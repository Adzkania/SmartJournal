# 📓 Smart Journal App

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white)
![Hugging Face](https://img.shields.io/badge/Hugging%20Face-FFD21E?style=for-the-badge&logo=huggingface&logoColor=000)

A Java-based journaling application with SQLite database, real-time weather integration, and AI-powered sentiment analysis.

> 🎓 **Course Project**: Fundamentals of Programming (FOP)  
> 👥 **Team**: Adzkania & Keertti  
> 📅 **Semester**: 1, 2024/2025

---

## 📑 Table of Contents

- [Features](#-features)
- [Prerequisites](#️-prerequisites)
- [Setup Instructions](#-setup-instructions)
- [How to Use](#-how-to-use)
- [Project Structure](#-project-structure)
- [API Documentation](#-api-documentation)
- [Troubleshooting](#-troubleshooting)
- [For Team Members](#-for-team-members)
- [Security Notes](#-security-notes)

---

## 📋 Features

- **User Authentication**: Secure login and registration system
- **SQLite Database**: Persistent data storage using relational database
- **Daily Journaling**: Create and edit journal entries with timestamps
- **Weather Integration**: Automatic weather fetching for Kuala Lumpur using Malaysian Government API
- **Sentiment Analysis**: AI-powered mood detection using Hugging Face's DistilBERT model
- **Weekly Summary**: View your mood trends over the past 7 days
- **Data Persistence**: All user data and journals saved in SQLite database

## 🛠️ Prerequisites

| Requirement | Version | Purpose |
|------------|---------|---------|
| ☕ Java JDK | 8 or higher | Core application |
| 🗄️ SQLite JDBC | 3.45.0.0+ | Database connectivity |
| 📦 JSON-Simple | 1.1.1+ | JSON parsing for APIs |
| 🤗 Hugging Face Account | Free tier | Sentiment analysis API |
| 🌐 Internet Connection | Required | Weather & sentiment APIs |

## 📥 Setup Instructions

### 1. Clone or Download the Project

```bash
git clone https://github.com/Adzkania/SmartJournal.git
cd SmartJournal
```

### 2. Download Required Libraries

You need to download two JAR files for the project to work with SQLite database and JSON parsing:

#### **📥 SQLite JDBC Driver**

<details>
<summary>Click here for download instructions</summary>

1. Go to [Maven Central - SQLite JDBC](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/)
2. Click on the latest version (e.g., `3.45.0.0/`)
3. Download `sqlite-jdbc-3.45.0.0.jar`

**Direct link**: [Download sqlite-jdbc-3.45.0.0.jar](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.45.0.0/sqlite-jdbc-3.45.0.0.jar)

</details>

#### **📥 JSON Simple Library**

<details>
<summary>Click here for download instructions</summary>

1. Go to [Maven Central - JSON Simple](https://repo1.maven.org/maven2/com/googlecode/json-simple/json-simple/)
2. Click on version `1.1.1/`
3. Download `json-simple-1.1.1.jar`

**Direct link**: [Download json-simple-1.1.1.jar](https://repo1.maven.org/maven2/com/googlecode/json-simple/json-simple/1.1.1/json-simple-1.1.1.jar)

</details>

#### **📁 Organize Your Files**

Create a `lib` folder in your project directory and place both JAR files there:

```
SmartJournal/
├── lib/
│   ├── sqlite-jdbc-3.45.0.0.jar
│   └── json-simple-1.1.1.jar
├── SmartJournalApp.java
├── User.java
├── JournalEntry.java
├── (other .java files)
├── .env
└── .gitignore
```

### 3. Get Your Hugging Face API Token

> 🔑 **You need a free Hugging Face account to use the sentiment analysis feature**

<details>
<summary>Click here for step-by-step instructions</summary>

1. Go to [Hugging Face](https://huggingface.co/join) and create a free account
2. Navigate to [Settings → Access Tokens](https://huggingface.co/settings/tokens)
3. Click **"Create new token"**
4. Give it a name (e.g., "Smart Journal App")
5. Select **"Read"** permission
6. Click **"Generate token"**
7. **Copy the token** (it starts with `hf_`)

</details>

### 4. Create the `.env` File

> ⚠️ **IMPORTANT**: This file contains your secret API token. Never commit it to Git!

Create a file named `.env` in the project root directory (same folder as the Java files).

<details>
<summary>📝 Instructions for Windows</summary>

**PowerShell:**
```powershell
notepad .env
```

</details>

<details>
<summary>📝 Instructions for Mac/Linux</summary>

```bash
nano .env
```

</details>

Add this line to the file (replace with your actual token):
```env
BEARER_TOKEN=hf_YourActualTokenHere
```

**Example:**
```env
BEARER_TOKEN=hf_XXXXXXXXXXXXXXXXX
```

**✅ Checklist:**
- [ ] No spaces around the `=` sign
- [ ] No quotes around the token
- [ ] File is named `.env` (not `.env.txt`)
- [ ] File is in the same folder as your `.java` files

### 5. Compile the Project

Navigate to the project directory and compile all Java files with the required libraries:

#### **Windows (PowerShell/CMD):**
```powershell
javac -cp ".;lib\*" *.java
```

#### **Mac/Linux:**
```bash
javac -cp ".:lib/*" *.java
```

This should compile all files without errors.

### 6. Run the Application

#### **Windows (PowerShell/CMD):**
```powershell
java -cp ".;lib\*" SmartJournalApp
```

#### **Mac/Linux:**
```bash
java -cp ".:lib/*" SmartJournalApp
```

## 📖 How to Use

### First Time Setup

1. **Register a new account**:
   - Choose option `2` (Register)
   - Enter your email
   - Choose a display name
   - Create a password

2. **Login**:
   - Choose option `1` (Login)
   - Enter your registered email and password

### Creating Journal Entries

1. From the main menu, select **"Create, Edit & View Journals"**
2. You'll see a list of dates - today's date will be marked as "(Today)"
3. Select today's date to create a new entry
4. Write your journal entry when prompted
5. The app will automatically:
   - Fetch current weather for Kuala Lumpur
   - Analyze the sentiment of your entry (Positive 😊 or Negative 😔)
   - Save everything to the SQLite database

### Viewing Past Entries

1. Go to **"Create, Edit & View Journals"**
2. Select any date from the list
3. View the journal content, weather, and mood for that day

### Editing Today's Entry

1. Go to **"Create, Edit & View Journals"**
2. Select today's date
3. Choose **"Edit Journal"**
4. Enter your updated text
5. The mood will be re-analyzed automatically

### Weekly Summary

1. From the main menu, select **"View Weekly Mood Summary"**
2. See your journal entries, weather, and moods for the past 7 days
3. Track your emotional patterns over time

## 📁 Project Structure

```
SmartJournal/
├── lib/
│   ├── sqlite-jdbc-3.45.0.0.jar    # SQLite database driver
│   └── json-simple-1.1.1.jar       # JSON parsing library
├── SmartJournalApp.java             # Main application with UI logic
├── User.java                        # User data model
├── JournalEntry.java                # Journal entry data model
├── FileHandler.java                 # Handles database operations
├── WeatherAPI.java                  # Fetches weather from Malaysian Gov API
├── SentimentAPI.java                # Analyzes sentiment using Hugging Face
├── EnvLoader.java                   # Loads environment variables from .env
├── .env                             # Your API token (DO NOT COMMIT TO GIT!)
├── .gitignore                       # Tells Git to ignore .env file
└── smartjournal.db                  # SQLite database (auto-generated)
```

## 🔒 Security Notes

- **Never share your `.env` file** or commit it to Git
- The `.gitignore` file prevents `.env` from being uploaded to GitHub
- Each user's password is stored in plain text in the database (for educational purposes only - not secure for production!)
- SQLite database file (`smartjournal.db`) contains all user data and should be backed up regularly

## 🐛 Troubleshooting

<details>
<summary><b>❌ "Error: BEARER_TOKEN is not set in the environment"</b></summary>

**Solutions:**
- Make sure your `.env` file exists in the same folder as the Java files
- Check that the format is exactly: `BEARER_TOKEN=hf_yourtoken` (no spaces, no quotes)
- Verify the file is named `.env` not `.env.txt`
- Try running: `Get-Content .env` (Windows) or `cat .env` (Mac/Linux) to check contents

</details>

<details>
<summary><b>🗄️ "ClassNotFoundException: org.sqlite.JDBC"</b></summary>

**Solutions:**
- Make sure `sqlite-jdbc-3.45.0.0.jar` is in the `lib` folder
- Verify you're compiling and running with the classpath: `-cp ".;lib\*"` (Windows) or `-cp ".:lib/*"` (Mac/Linux)
- Check that the JAR file is not corrupted - try re-downloading it

</details>

<details>
<summary><b>📦 "Package org.json.simple does not exist"</b></summary>

**Solutions:**
- Make sure `json-simple-1.1.1.jar` is in the `lib` folder
- Ensure you're including the lib folder in your classpath when compiling
- Verify the JAR file downloaded correctly (should be around 24 KB)

</details>

<details>
<summary><b>🌦️ "Weather unavailable" or 😔 "Mood unavailable"</b></summary>

**Solutions:**
- Check your internet connection
- For mood analysis: Verify your Hugging Face token is valid at [huggingface.co/settings/tokens](https://huggingface.co/settings/tokens)
- The token might be expired - generate a new one
- Check if the APIs are working by visiting them in your browser

</details>

<details>
<summary><b>🔨 Compilation errors</b></summary>

**Solutions:**
- Make sure all `.java` files are in the same directory
- Ensure you're using Java 8 or higher: `java -version`
- Include the lib folder in classpath: `javac -cp ".;lib\*" *.java`
- Try cleaning up: Delete all `.class` files and compile again
- Check for syntax errors in your code

</details>

<details>
<summary><b>🚫 "Cannot find or load main class SmartJournalApp"</b></summary>

**Solutions:**
- Make sure you compiled first with the correct classpath
- Run with the correct classpath: `java -cp ".;lib\*" SmartJournalApp`
- Check that `SmartJournalApp.class` exists in the directory
- Ensure you're in the correct directory

</details>

<details>
<summary><b>💾 "Database is locked" or "Database error"</b></summary>

**Solutions:**
- Close any other programs that might be accessing `smartjournal.db`
- Make sure you're not running multiple instances of the application
- If the database is corrupted, delete `smartjournal.db` and restart the app (you'll lose all data)
- Check file permissions - the app needs write access to create the database

</details>

## 🤝 For Team Members

### 🔄 Git Workflow

```bash
# Before you start working
git pull origin main

# After making changes
git add .
git commit -m "feat: add your feature description"
git push origin main
```

### 📋 Commit Message Format

Use these prefixes for clear commit history:
- `feat:` - New feature
- `fix:` - Bug fix
- `docs:` - Documentation changes
- `style:` - Code formatting
- `refactor:` - Code restructuring

**Examples:**
```bash
git commit -m "feat: add password validation"
git commit -m "fix: resolve login authentication bug"
git commit -m "docs: update README with new features"
```

### ⚠️ Important Rules

| ✅ DO | ❌ DON'T |
|-------|----------|
| Test your changes locally first | Commit the `.env` file |
| Pull before you push | Push broken code |
| Write clear commit messages | Make large commits without explanation |
| Ask for help when stuck | Delete other people's code without discussion |
| Include lib folder with JARs | Commit the `smartjournal.db` file |

### 👥 File Responsibilities

| File | Purpose | Primary Owner |
|------|---------|---------------|
| `SmartJournalApp.java` | Main UI and menu logic | [Team Lead] |
| `FileHandler.java` | Database operations | [Member 2] |
| `WeatherAPI.java` | Weather data fetching | [Member 3] |
| `SentimentAPI.java` | Sentiment analysis | [Member 4] |
| `User.java` / `JournalEntry.java` | Data models | [Shared] |

## 📝 API Documentation

### SQLite Database
- **Driver**: Xerial SQLite JDBC
- **Version**: 3.45.0.0
- **Connection String**: `jdbc:sqlite:smartjournal.db`
- **Tables**: `users`, `journal_entries`

### Weather API
- **Source**: Malaysian Government Open Data API
- **Endpoint**: `https://api.data.gov.my/weather/forecast/`
- **Authentication**: None required
- **Rate Limit**: Reasonable use
- **JSON Parsing**: Uses json-simple library

### Sentiment Analysis API
- **Source**: Hugging Face Inference API
- **Model**: `distilbert-base-uncased-finetuned-sst-2-english`
- **Authentication**: Bearer token required
- **Rate Limit**: Check your [Hugging Face account](https://huggingface.co/settings/tokens)
- **JSON Parsing**: Uses json-simple library

## 📧 Support

If you encounter issues:

1. 📖 Check the [Troubleshooting](#-troubleshooting) section above
2. 💬 Ask in the team group chat
3. 🐛 [Create an issue](https://github.com/Adzkania/SmartJournal/issues) on GitHub
4. 📧 Contact the team lead

---

<div align="center">

## 📄 License

This project is for educational purposes as part of the **Fundamentals of Programming** course.

---

**Made with ☕ and 💻 by Team Adzkania**

**Happy Journaling! 📓✨**

[![GitHub](https://img.shields.io/badge/GitHub-SmartJournal-blue?style=flat&logo=github)](https://github.com/Adzkania/SmartJournal)

</div>
