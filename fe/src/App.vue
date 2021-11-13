<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
@import "../src/assets/scss/common/extend.scss";
@import "../src/assets/scss/common/fonts.scss";
@import "../src/assets/scss/common/minxi.scss";
@import "../src/assets/scss/common/variables.scss";
@import "../src/assets/scss/common/global.scss";

@import "../src/assets/scss/layouts/main.scss";

@import "../src/assets/scss/layouts/responsive.scss";
@import "../src/assets/scss/layouts/main.scss";
@import "../src/assets/css/style.css";
@import "../src/assets/fonts/material-icon/css/material-design-iconic-font.css";
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
