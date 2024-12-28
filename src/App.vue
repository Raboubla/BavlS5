<template>
  <div :class="{ 'auth-layout': $route.meta.hideHeader }">
    <Header v-if="!$route.meta.hideHeader" />
    <router-view />
    <Footer v-if="!$route.meta.hideHeader" />
  </div>
</template>

<script setup>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
</script>

<style>
:root {
  --color-primary: #8B4513;
  --color-secondary: #F5E6D3;
  --color-light: #FFF9F0;
  --color-dark: #2C1810;
  --color-accent: #E63946;
  --color-background: #FAF3E0;
  --color-text: #4A3728;
  --color-gold: #D4A373;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Helvetica Neue', Arial, sans-serif;
  line-height: 1.6;
  color: var(--color-dark);
  background-color: var(--color-background);
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 15px;
}

/* Style pour les pages d'authentification */
.auth-layout {
  min-height: 100vh;
  background-image: linear-gradient(rgba(44, 24, 16, 0.8), rgba(44, 24, 16, 0.8)), url('./assets/bread.jpg');
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
  display: flex;
  flex-direction: column;
}

.auth-layout > * {
  flex: 1;
}
</style>
