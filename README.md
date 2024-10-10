# ObjectWrapper

### How to use it:

```java
// Create instance of ObjectWrapper
ObjectWrapper objectWrapper = new ObjectWrapper(File);

// Add data to map
objectWrapper.putData(new DataString("test", "this is test"));
objectWrapper.putData(new DataInt("testId", 10));


// Save all data to file
objectWrapper.save();

// Returns data by id
DataString test = objectWrapper.readData("test");
DataInt testId = objectWrapper.readData("testId");
```

### Types of data:
- DataObject
- DataString
- DataBoolean
- DataInt
- DataDouble
- DataFloat
- DataByte
- DataClass
- and more...

### Benefits:
- It's easy to use
- It's legible (In my opinion)
- The file you are writing data to is unreadable

By JustDrven
