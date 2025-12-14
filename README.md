# 🍬 Sweet Shop Management System – Android Frontend

An **Android application** built using **Kotlin** and **Jetpack Compose** that consumes a **FastAPI backend** to provide a modern, responsive Sweet Shop experience.

This app follows **MVVM architecture**, uses **Retrofit for networking**, **StateFlow for state management**, and **Material 3 UI** to deliver a clean and scalable mobile application, and backend is in **main** Branch.

---

## 📱 App Overview

The Android frontend serves as the **user-facing interface** of the Sweet Shop Management System.

Users can:
- View available sweets
- See prices and stock availability
- Purchase sweets (stock updates in real time)
- View out-of-stock states clearly
- Enjoy a fast, offline-friendly UI using local drawable images

---

## 🎯 What This Frontend Does

- Connects to a FastAPI backend via REST APIs
- Displays sweets fetched from the backend
- Handles purchase actions securely
- Shows loading, empty, and error states
- Uses local images (`R.drawable`) by default for reliability
- Separates UI and business logic using MVVM

---

## 🧩 Key Features

### 🏠 Home Screen
- List of all sweets
- Sweet image, name, price, and stock
- Purchase button (disabled when out of stock)

### 🔄 State Handling
- Loading indicator while fetching data
- Empty state when no sweets are available
- Automatic refresh after purchase

### 🖼️ Image Handling
- Images loaded from `res/drawable`
- Backend image URLs supported for future use
- Safe default image fallback

### 🧪 Preview-Safe UI
- Pure composables separated from ViewModel
- Android Studio Preview works without backend

---

## 🏗️ Architecture (MVVM)
UI (Jetpack Compose)
│
├── ViewModel (StateFlow)
│
├── Repository (Networking)
│
└── Retrofit API Client

---


### Why MVVM?
- Clear separation of concerns
- Testable business logic
- Scalable for future features
- Industry-standard Android architecture

---

## 🛠️ Tech Stack

### 🔹 Language & UI
- **Kotlin**
- **Jetpack Compose**
- **Material 3**

### 🔹 Architecture & State
- **MVVM**
- **ViewModel**
- **StateFlow / MutableStateFlow**

### 🔹 Networking
- **Retrofit**
- **OkHttp**
- **Gson Converter**
- **HTTP Logging Interceptor**

### 🔹 Tooling
- Android Studio
- Emulator / Physical device

---

## 🔗 Backend Integration

The app connects to the FastAPI backend using Retrofit.

### Base URL (Emulator)
http://10.0.2.2:8000/

--- 

### APIs Used
- `GET /api/sweets` → Fetch all sweets
- `POST /api/sweets/{id}/purchase` → Purchase a sweet

---

## 📸 Screenshot

### 🏠 Home Screen (Sweet Listing)

<img 
  src="https://github.com/user-attachments/assets/e4e7ea28-9d1e-4ccf-9d13-dc6c267df11e" 
  alt="Sweet Shop Home Screen" 
  width="275" 
  height="581"
/>

**Description:**
- Displays all available sweets fetched from the FastAPI backend
- Shows sweet image (from local drawable), name, price, and stock
- Purchase button is automatically disabled when stock is zero
- Clean Material 3 UI built with Jetpack Compose

---
