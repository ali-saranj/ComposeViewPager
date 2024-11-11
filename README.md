# ComposeLearn - Introduction Screen with Horizontal Pager

![Horizontal Pager](https://miro.medium.com/v2/resize:fit:750/format:webp/1*ZozfUVF9VACNO3iqoWdrtw.png)


This project demonstrates a simple **introduction screen** created using **Jetpack Compose**. It includes a horizontal pager to navigate through different intro pages and a bottom sheet with navigation controls.

## Features

- **Horizontal Pager**: Navigate through intro pages horizontally with animations.
- **Customizable Bottom Sheet**: Each page displays a unique title, description, and background color in a stylish bottom sheet.
- **Page Indicator**: An indicator at the top of the bottom sheet shows the current page.
- **Navigation Controls**: Buttons for navigating forward and backward between pages.

## Code Structure

### 1. `IntroScreen`
This is the main composable function that sets up the overall UI layout. It initializes the `pagerState` and provides a list of `IntroPage` objects, each containing a title, description, image, and background color for each intro page. The `IntroPager` and `BottomSheet` composables are added within a `Box` layout to stack the content vertically.

```kotlin
@Composable
fun IntroScreen(modifier: Modifier = Modifier) {
    // Initialization and UI setup code
}
```

### 2. `IntroPage`
A data class that represents each page in the pager, containing:
- `title`: The title of the page.
- `description`: Brief description of the page.
- `image`: Image resource ID.
- `color`: Background color for the page.

```kotlin
data class IntroPage(val title: String, val description: String, val image: Int, val color: Color)
```

### 3. `BottomSheet`
Displays the title, description, and navigation buttons for each page. It also contains the page indicator, which shows the current page position. The back and forward buttons allow users to navigate between pages. The `rememberCoroutineScope` is used for smooth page transitions.

```kotlin
@Composable
fun BottomSheet(modifier: Modifier = Modifier, pagerState: PagerState, pages: List<IntroPage>) {
    // Content layout and navigation button setup
}
```

### 4. `PageIndicator`
A row of small circles indicating the current page position. The `slidingLineTransition` modifier applies an animation to the indicator as the user scrolls through pages.

```kotlin
@Composable
fun PageIndicator(pagerState: PagerState, pages: List<IntroPage>) {
    // Page indicator setup
}
```

### 5. `slidingLineTransition`
An extension function that applies a sliding transition effect to the page indicator. It calculates the horizontal offset of the indicator based on the current page index and offset fraction.

```kotlin
private fun Modifier.slidingLineTransition(pagerState: PagerState, distance: Float) = graphicsLayer {
    // Animation code for page indicator
}
```

### 6. `IntroPager`
A horizontal pager that displays each page's content using the `IntroPageContent` composable.

```kotlin
@Composable
fun IntroPager(pages: List<IntroPage>, pagerState: PagerState) {
    // Horizontal pager setup
}
```

### 7. `IntroPageContent`
Displays the background color and an image for each intro page. The image is scaled to fit the content size.

```kotlin
@Composable
fun IntroPageContent(page: IntroPage) {
    // Page content layout with image and background color
}
```

### Preview
To preview the UI, use `PreviewIntroScreen` which calls `IntroScreen()` within an Android Studio preview environment.

```kotlin
@Preview(showBackground = true)
@Composable
fun PreviewIntroScreen() {
    IntroScreen()
}
```

## Setup and Usage

To use this code:

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/ComposeLearn.git
   cd ComposeLearn
   ```

2. **Run the Project**
    - Open in Android Studio.
    - Run the project on an emulator or device.

