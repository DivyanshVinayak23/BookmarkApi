from kafka import KafkaProducer
import json

def send_bookmark(title, url, description, tags):
    producer = KafkaProducer(
        bootstrap_servers=['localhost:9092'],
        value_serializer=lambda x: json.dumps(x).encode('utf-8')
    )
    
    bookmark = {
        "title": title,
        "url": url,
        "description": description,
        "tags": tags
    }
    
    producer.send('bookmark-topic', value=bookmark)
    producer.flush()
    print("Bookmark sent successfully!")

if __name__ == "__main__":
    # Example usage
    send_bookmark(
        title="Python Kafka Test",
        url="https://python-kafka-test.com",
        description="Testing Kafka with Python producer",
        tags=["python", "kafka", "test"]
    ) 