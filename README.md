# Browser Navigation System

## Overview
A Java-based browser navigation simulator that mimics real-world back/forward browsing using custom stack and queue data structures. Implements session saving and restoring functionality.

## Features
- Visit, go back, and go forward between pages
- View and clear browsing history
- Save and restore sessions from file
- Persistent storage of session data

## Components
- **BrowserLinkedList:** Doubly linked list implementation
- **BrowserArrayList:** Circular array implementation
- **BrowserStack / BrowserQueue:** For navigation and history
- **BrowserNavigation:** Main menu-driven interface
- **StackIterator:** Custom iterator for saving/restoring sessions

## File Handling
Session data saved to `session_data.txt` with separate sections for:
- Forward stack  
- Backward stack  
- Browsing history  

## Complexity
- Stack & Queue operations: O(1)
- Session save/restore: O(n)
- History operations: O(1)–O(n)
