from kafka import KafkaProducer
import json
from datetime import datetime
import os
import time

def send_bookmark(title, url, description, tags):
    # Get Kafka bootstrap servers from environment variable or use default
    bootstrap_servers = os.environ.get('KAFKA_BOOTSTRAP_SERVERS', 'localhost:9092')
    
    producer = KafkaProducer(
        bootstrap_servers=bootstrap_servers.split(','),
        value_serializer=lambda x: json.dumps(x).encode('utf-8')
    )
    
    bookmark = {
        "title": title,
        "url": url,
        "description": description,
        "tags": tags,
        "createdAt": datetime.now().isoformat(),
        "updatedAt": datetime.now().isoformat()
    }
    
    try:
        producer.send('bookmark-topic', value=bookmark)
        producer.flush()
        print("Bookmark sent successfully!")
        return True
    except Exception as e:
        print(f"Error sending bookmark: {str(e)}")
        return False
    finally:
        producer.close()

def interactive_mode():
    """Run in interactive mode, allowing user to input bookmarks"""
    print("=== Bookmark Kafka Producer ===")
    print("Enter bookmark details (or 'exit' to quit):")
    
    while True:
        try:
            title = input("Title: ")
            if title.lower() == 'exit':
                break
                
            url = input("URL: ")
            description = input("Description: ")
            tags_input = input("Tags (comma-separated): ")
            tags = [tag.strip() for tag in tags_input.split(',') if tag.strip()]
            
            if send_bookmark(title, url, description, tags):
                print("\nBookmark sent! Enter another or type 'exit' to quit.\n")
            else:
                print("\nFailed to send bookmark. Try again or type 'exit' to quit.\n")
                
        except KeyboardInterrupt:
            print("\nExiting...")
            break

if __name__ == "__main__":
    # Wait for Kafka to be ready
    time.sleep(5)
    
    # Check if we should run in interactive mode or just send the example
    if os.environ.get('INTERACTIVE_MODE', 'false').lower() == 'true':
        interactive_mode()
    else:
        # Example usage
        send_bookmark(
            title="Python Kafka Test",
            url="https://python-kafka-test.com",
            description="Testing Kafka with Python producer",
            tags=["python", "kafka", "test"]
        )
        print("Example bookmark sent. Set INTERACTIVE_MODE=true to run in interactive mode.") 