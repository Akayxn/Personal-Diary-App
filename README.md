# Simple Diary Application

A straightforward console-based diary application that allows you to write, read, and manage daily diary entries.

## Features

- **Write Diary Entries**: Create a new diary entry for the current date
- **Read Past Entries**: Browse and read previously written diary entries
- **Delete Entries**: Remove unwanted diary entries

## Technical Details

- Written in Java
- Uses file I/O operations to store diary entries as text files
- Entries are stored in the `src/main/java/` directory

## Getting Started

Clone the repository and run the `App.java` file to start using the application.

```
git clone [your-repository-url]
cd [repository-name]
javac App.java
java App
```

## Notes

- You can only create one diary entry per day
- To finish writing an entry, type `diary.close`
- Each entry is saved with your pen name
