# Nom Nom Notifier

[![CI Status](https://github.com/se-edu/addressbook-level3/workflows/Java%20CI/badge.svg)](https://github.com/se-edu/addressbook-level3/actions)

![Ui](docs/images/Ui.png)

## Introduction

Nom Nom Notifier is a simple command-lind application designed for small eateries or restaurants to manage the customer details efficiently for delivery purposes.

## Features
- **Add**: Add new contacts to your address book with their name, phone number, and other details.
- **Delete**: Remove contacts from your address book.
- **Remark**: Add remarks to existing contacts.
- **Find**: Find a customer by name, number, email address, address, postal code or tags.

## Usage
After running the application, you can entier commands to manage your address book. Below is a list of commands and their usage.

## Commands
- **Add a Contact**
    ```
    add n/NAME p/PHONE_NUMBER
    ```
  Example:
    ```
    add n/John Doe p/12345678
    ```
- **Delete a Contact**
  ```
  delete INDEX
  ```
  Example:
  ```
  delete 1
  ```
- **Find a Contact**
    ```
    find KEYWORD
    ```
  Example:
    ```
    find John
    ```
- **Add Remark to a Contact**
    ```
    remark INDEX REMARK
    ```
  Example:
    ```
    remark 1 Likes coffee
    ```