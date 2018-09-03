NicerXVI
========

Base16 string encoding and decoding intended for use with short user-visible identifiers.

## Purpose

Convert byte sequences to and from human readable strings of text made up of a limited set of
unambiguous letters. The sixteen letters used are:

```
x c d f g k m p q r s t v w b z
```

Vowels are omitted to prevent strings from unintentionally containing words.

## Installation

This library is available on Maven Central:

```
<dependency>
    <groupId>org.lable.oss.nicerxvi</groupId>
    <artifactId>nicerxvi</artifactId>
    <version>1.2</version>
</dependency>
```

## Example usage

```java
byte[] inputByteValue = "TEST1234".getBytes();

String nicerXVIString = NicerXVI.encode(inputByteValue);

// nicerXVIString = "kggkkfkgfcfdfffg";

byte[] outputByteValue = NicerXVI.decode(nicerXVIString);

// Arrays.equals(inputByteValue, outputByteValue) == true
```
