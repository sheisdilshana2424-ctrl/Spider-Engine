# 🚀 Level Up: Daily Growth

**A personal productivity Android app developed by Shahil**

![App Icon](assets/icons/icon-192x192.png)

## 📱 Features

- ✅ **Dual Mode Task Management**
  - Full Focus Day mode
  - College / Busy Day mode
  
- 🔥 **Streak Tracking** - Keep your daily progress streak alive

- 📊 **Visual Progress Bars** - See your daily and long-term progress

- 📝 **Daily Learning Log** - Record what you learned each day

- 📅 **History Tracking** - View your past achievements

- 🎉 **Celebration Effects** - Confetti animation when you complete your day

- 🌙 **Dark Theme** - Easy on the eyes with beautiful gradients

## 🚀 Getting Started

### Method 1: GitHub Actions (Recommended for Mobile Users)

1. **Fork this repository** to your GitHub account
   - Tap the "Fork" button at the top right

2. **Enable GitHub Actions**
   - Go to the "Actions" tab in your forked repo
   - Click "I understand my workflows, go ahead and enable them"

3. **Trigger the Build**
   - Go to "Actions" tab → "Build Android APK"
   - Click "Run workflow" → "Run workflow"

4. **Download Your APK**
   - Wait for the workflow to complete (about 5-10 minutes)
   - Click on the completed workflow run
   - Scroll down to "Artifacts" section
   - Download "LevelUp-APK"

5. **Install on Android**
   - Transfer the APK to your phone
   - Enable "Install from Unknown Sources" in Settings → Security
   - Tap the APK file to install

### Method 2: Local Build (For Developers)

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/ShahilTodoApp.git
cd ShahilTodoApp

# Install dependencies
npm install

# Add Android platform
npx cap add android

# Sync files
npx cap sync

# Build APK
cd android
./gradlew assembleRelease

# Find your APK at:
# android/app/build/outputs/apk/release/app-release.apk
```

## 📂 Project Structure

```
ShahilTodoApp/
├── .github/
│   └── workflows/
│       └── build-apk.yml      # GitHub Actions workflow
├── assets/
│   └── icons/                  # App icons in all sizes
│       ├── icon-72x72.png
│       ├── icon-96x96.png
│       ├── icon-128x128.png
│       ├── icon-144x144.png
│       ├── icon-152x152.png
│       ├── icon-192x192.png
│       ├── icon-384x384.png
│       └── icon-512x512.png
├── index.html                  # Main app (all-in-one HTML)
├── manifest.json               # PWA manifest
├── capacitor.config.json       # Capacitor configuration
├── package.json                # Node dependencies
├── .gitignore                  # Git ignore rules
└── README.md                   # This file
```

## 🎨 App Preview

### Splash Screen
The app features a beautiful animated splash screen with:
- Animated rocket logo
- "App Developed by Shahil" text with cool animations
- Loading dots animation

### Main Features
- **Mode Switcher**: Toggle between Focus and College modes
- **Task List**: Check off daily tasks with satisfying animations
- **Progress Tracking**: Visual progress bars for daily and long-term goals
- **Learning Log**: Write notes about your daily learning
- **History**: View all your completed days

## 🛠️ Technologies Used

- **HTML5** - Structure
- **CSS3** - Styling with custom properties and animations
- **JavaScript** - App logic and local storage
- **Capacitor** - Native Android wrapper
- **GitHub Actions** - CI/CD for automated builds

## 📋 System Requirements

- **Android**: 5.0 (API 21) or higher
- **Storage**: ~10 MB
- **Internet**: Required for CDN resources (fonts, icons)

## 🔒 Permissions

The app requires minimal permissions:
- `INTERNET` - For loading fonts and icons from CDN
- `ACCESS_NETWORK_STATE` - To check network connectivity

## 🐛 Troubleshooting

### Build Fails
1. Check that all files are committed
2. Ensure GitHub Actions is enabled
3. Try re-running the workflow

### App Won't Install
1. Enable "Install from Unknown Sources" in Settings
2. Make sure you downloaded the correct APK file
3. Check your Android version (needs 5.0+)

### Data Not Saving
- The app uses browser localStorage
- Data is saved per device
- Clearing browser/app data will reset progress

## 📝 License

MIT License - Feel free to use and modify!

## 👨‍💻 Developer

**Shahil**

Built with ❤️ for daily productivity and growth.

---

## 🌟 Support

If you like this app, please ⭐ star the repository!

For issues or feature requests, please open an issue on GitHub.
