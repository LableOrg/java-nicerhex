NicerHex 
========

Base16 string encoding and decoding intented for use with short user-visible identifiers.

## Purpose

Convert byte sequences to and from human readable strings of text made up of a limited set of
unambiguous letters. The sixteen letters used are:

```
x c d f g k m p q r s t v w b z
```

Vowels are omitted to prevent strings from containing unintended words.

## Installation

This library is available on Maven Central:

```
<dependency>
    <groupId>org.lable.oss.nicerhex</groupId>
    <artifactId>nicerhex</artifactId>
    <version>1.0</version>
</dependency>
```

## Example usage

```java
byte[] inputByteValue = "TEST1234".getBytes();

String nicerHexString = NicerHex.encode(inputByteValue);

// nicerHexString = "kggkkfkgfcfdfffg";

byte[] outputByteValue = NicerHex.decode(nicerHexString);

// Arrays.equals(inputByteValue, outputByteValue) == true
```
