<template>
  <div class="page-hero">
    <h1>{{ title }}</h1>
  </div>
</template>

<script setup>
defineProps({
  title: {
    type: String,
    required: true
  }
})
</script>

<style scoped>
.page-hero {
  height: 300px;
  background-image: linear-gradient(rgba(44, 24, 16, 0.7), rgba(44, 24, 16, 0.7)), url('../assets/bread.jpg');
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 40px;
}

.page-hero h1 {
  color: white;
  font-size: 3em;
  text-align: center;
  margin: 0;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

@media (max-width: 768px) {
  .page-hero {
    height: 200px;
  }

  .page-hero h1 {
    font-size: 2em;
  }
}
</style> 