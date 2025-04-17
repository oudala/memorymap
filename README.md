# Custom HashMap Implementation

A custom implementation of a HashMap data structure in Java that stores integer keys and String values. This implementation includes features like linear probing for collision resolution, automatic resizing, and string pooling for memory optimization.

## Features

- Integer key to String value mapping
- Linear probing collision resolution
- Automatic resizing when load factor exceeds 0.7
- String pooling for memory optimization
- Serialization support
- Performance benchmarking tools

## Project Structure

```
custom-hashmap/
├── src/
│   ├── main/java/com/ilyass/memorymap/
│   │   ├── MemoryHashMap.java      # Main HashMap implementation
│   │   ├── HashFunction.java       # Hash function utility
│   │   ├── PoolManager.java        # String pooling implementation
│   │   ├── Main.java              # Demo application
│   │   ├── BenchmarkProfiler.java  # Performance testing
│   │   └── fortest.java           # Comparison with Java HashMap
│   └── test/java/
│       └── MemoryHashMapTest.java  # Unit tests
└── pom.xml                         # Maven configuration
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.x

### Building the Project

```bash
mvn clean install
```

### Running Tests

```bash
mvn test
```

## Usage Example

```java
MemoryHashMap map = new MemoryHashMap();

// Adding elements
map.put(1, "One");
map.put(2, "Two");

// Retrieving elements
String value = map.get(1); // Returns "One"

// Removing elements
map.remove(1);

// Serialization
map.saveToFile("map_dump.ser");
MemoryHashMap loadedMap = MemoryHashMap.loadFromFile("map_dump.ser");
```

## Key Features Implementation

### Linear Probing
Handles collisions by searching for the next available slot in the array.

### Automatic Resizing
Doubles the capacity when the load factor exceeds 0.7 to maintain performance.

### String Pooling
Optimizes memory usage by reusing String instances through the `PoolManager`.

## Performance

Performance can be tested using the `BenchmarkProfiler` class which measures:
- Insertion time
- Serialization time
- Deserialization time

## License

This project is open source and available under the MIT License.

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request
