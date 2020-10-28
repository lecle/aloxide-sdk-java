# Aloxide

A wrapper class to read, write data to EOS/ICON Network

## prerequisite

- JDK 11.0.8 [https://www.oracle.com/java/technologies/javase-jdk11-downloads.html](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

- Use the package manager [gradle](https://gradle.org/).

:grey_exclamation: Recommend: install Android Studio IDE which includes `gradle` and `jdk` inside


### Example run with gradle in command line:
:warning: **Edit `env.property` file to use Blockchain network you want**

To add, by run:
```gradle
gradle ADD --args="Poll 123 Name Body"

output:
--------------- YOUR RESULT HERE ---------------
Transaction ID: 0x927374a3f208b1a5640b9a12fceeee562925135d6c4c1aac5aba44744b837f4b
Check information: https://bicon.tracker.solidwallet.io/transaction/0x927374a3f208b1a5640b9a12fceeee562925135d6c4c1aac5aba44744b837f4b
------------------------------------------------

```

To get, by run:
```gradle
gradle GET --args="Poll 123"

output:
--------------- YOUR RESULT HERE ---------------
{"id":"123","name":"Name","body":"Body"}
------------------------------------------------

```
To update, by run:
```gradle
gradle UPDATE --args="Poll 123 Name Body"
```

To delete, by run:
```gradle
gradle DELETE --args="Poll 123"
```

## Dependencies

Use for EOS Network
```gradle
implementation group: 'io.jafka', name: 'jeos', version: '0.9.15'
```

Use for ICON Network
```gradle
implementation 'foundation.icon:icon-sdk:1.0.0'
implementation 'com.squareup.okhttp3:okhttp:4.9.0'
```

## Usage

**Blockchain account initialization**
```java
 BlockchainAccount bcAccount = new BlockchainAccount.BlockchainAccountBuilder()
                .setName(/*your account name*/) // EOS network needed
                .setPrivateKey(/*your private key*/) // EOS network needed
                .setAddress(/*your address*/)// ICON network needed
                .build();
```

**Aloxide initialization**

```java
Aloxide poll = AloxideBuilder.newBuilder()
                .setNetwork(Network.EOS)
                .setUrl(/*your node url*/)
                .setBlockchainAccount(/*your blockchain account information*/)
                .setEnityName(/*your entity name*/)
                .setContract(/*the contract*/)
                .build();
```

```**These samples assume you deployed the SmartContract/SCORE like Poll{id, name, body}**```

**To add new record to blockchain, by run**

Example: Add new object `id=9999`, `name="NEW NAME"`, `body="NEW BODY"`. Return the Object dynamic you can use to cast/parse this result (`Poll poll = Poll.from(result);`).


```java
Map<String, String> d = new HashMap<>();
d.put("id", "9999");
d.put("name", "NEW NAME");
d.put("body", "NEW BODY");
Object result = poll.add(d);
```
**To get the record to blockchain by id, by run**

Example: Get the object by `id=9999`.


```java
Map<String, String> d = new HashMap<>();
Object result = poll.get("9999");
```
**To update the record to blockchain by id, by run**

Example: Update the object by `id=9999` with new data `name="NEW NAME updated"`, `body="NEW BODY updated"`.


```java
Map<String, String> d = new HashMap<>();
d.put("id", "9999");
d.put("name", "NEW NAME updated");
d.put("body", "NEW BODY updated");
Object result = poll.update("9999", d);
```

**To delete the record to blockchain by id, by run**

Example: Delete the object by `id=9999`.


```java
Map<String, String> d = new HashMap<>();
Object result = poll.delete("9999");
```

# License

[Apache License 2.0](./LICENSE)