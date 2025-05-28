from kafka import KafkaProducer
import json
from datetime import datetime

def send_bookmark(title, url, description, tags):
    producer = KafkaProducer(
        bootstrap_servers=['localhost:9092'],
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
    except Exception as e:
        print(f"Error sending bookmark: {str(e)}")
    finally:
        producer.close()

if __name__ == "__main__":

    send_bookmark(
        title="Python Kafka Test",
        url="https://python-kafka-test.com",
        description="Testing Kafka with Python producer",
        tags=["python", "kafka", "test"]
    ) 