# Autokey Cipher with Hash Verification

A Java implementation of the Autokey Cipher with extended character support and XOR-based hash verification.

## 📋 Overview

This project implements an **Autokey Cipher** - a polyalphabetic substitution cipher that uses a key and the plaintext itself as subsequent key material. The implementation supports 66 different characters including:
- Lowercase letters (a-z)
- Uppercase letters (A-Z)  
- Numbers (0-9)
- Special characters (., ?, !, ,)

## ✨ Features

- **Extended Character Set**: Supports 66 characters including letters, numbers, and punctuation
- **Autokey Encryption**: Uses the plaintext itself as the key source after the initial key is exhausted
- **Autokey Decryption**: Recovers the plaintext using previously decrypted characters as key material
- **Hash Verification**: Generates an XOR-based rotating hash for integrity checking
- **Console Interface**: Simple command-line interaction

## 🔧 How It Works

### Character Mapping
Characters are mapped to numbers 0-65:
- `a-z` → 0-25
- `A-Z` → 26-51  
- `0-9` → 52-61
- `.` → 62
- `?` → 63
- `!` → 64
- `,` → 65

### Encryption Process
For each character in the plaintext:
1. If within initial key length: use key character as cipher key
2. Otherwise: use previous plaintext character as cipher key
3. Combine: `cipher_num = (plain_num + key_num) % 66`

### Decryption Process  
For each character in the ciphertext:
1. If within initial key length: use key character as cipher key
2. Otherwise: use previously decrypted character as cipher key
3. Recover: `plain_num = (cipher_num - key_num + 66) % 66`

### Hash Generation
The XOR rotating hash provides a fingerprint of the ciphertext:
- XOR each character's numeric value with current hash
- Rotate hash left by 5 bits after each XOR

