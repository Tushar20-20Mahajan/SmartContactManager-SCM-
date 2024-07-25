// Here we will write all the JS in the project
/// To check whether the script is loaded or not
console.log("Script Loaded");

// Default Theme
let currentTheme = getTheme();
console.log(currentTheme);

// Initialize --->
document.addEventListener("DOMContentLoaded", () => {
  console.log("DOM Loaded");
  changeTheme();
  setLogo(currentTheme); // Set the initial logo based on the theme
});

// Function to change the theme
function changeTheme() {
  // Set the Web Page
  document.querySelector("html").classList.add(currentTheme);

  // set the listener to change the theme button
  const changeThemeButton = document.querySelector("#theme_Change_Button");
  // Changing the text from dark to light vice versa
  changeThemeButton.querySelector("span").textContent =
    currentTheme == "light" ? "Dark" : "Light";

  changeThemeButton.addEventListener("click", (event) => {
    const oldTheme = currentTheme;
    console.log("Current Theme Button Pressed");

    if (currentTheme == "dark") {
      // DO it light
      currentTheme = "light";
    } else {
      // Do it Dark
      currentTheme = "dark";
    }

    // Update in the Local Storage
    setTheme(currentTheme);

    // remove the current Theme (old Theme)
    document.querySelector("html").classList.remove(oldTheme);

    // set the current theme
    document.querySelector("html").classList.add(currentTheme);

    // Changing the text from dark to light vice versa
    changeThemeButton.querySelector("span").textContent =
      currentTheme == "light" ? "Dark" : "Light";

    // Update the logo based on the theme
    setLogo(currentTheme);
  });
}

// Function to set the current theme to local storage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

// Function to get the current theme to local storage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}

// Function to set the logo based on the theme
function setLogo(theme) {
  const logo = document.querySelector("#logoTusharSCM");
  if (theme === "dark") {
    logo.src = "/assets/TelePhoneLogoDark.png";
  } else {
    logo.src = "/assets/TelePhoneLogo.png";
  }
}
