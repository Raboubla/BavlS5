<template>
  <header>
    <div class="top-bar">
      <div class="container">
        <div class="top-bar-content">
          <p class="boulangerie-text">Mihinana finaritra miaraka am Boublangerie</p>
          <div class="top-bar-contact">
            <a href="tel:+00123456789">
              <i class="fas fa-phone"></i>
              +00 123 456 789
            </a>
            <a href="mailto:demo@example.com">
              <i class="fas fa-envelope"></i>
              demo@example.com
            </a>
            <a href="/login" class="account-link">
              <i class="fas fa-user"></i>
              Account
            </a>
          </div>
        </div>
      </div>
    </div>

    <div class="main-header">
      <div class="container">
        <div class="header-content">
          <div class="logo">
            <img src="../assets/boublangerie1.png" alt="Bucker">
          </div>
          
          <nav class="main-nav">
            <ul>
              <li>
                <router-link to="/" class="nav-link">
                  <span :class="{ 'nav-bg': true, 'active': $route.path === '/' }">HOME</span>
                </router-link>
              </li>
              <li>
                <router-link to="/about" class="nav-link">
                  <span :class="{ 'nav-bg': true, 'active': $route.path === '/about' }">ABOUT</span>
                </router-link>
              </li>
              <li>
                <router-link to="/pages" class="nav-link">
                  <span :class="{ 'nav-bg': true, 'active': $route.path === '/pages' }">PAGES</span>
                </router-link>
              </li>
              <li>
                <router-link to="/shop" class="nav-link">
                  <span :class="{ 'nav-bg': true, 'active': $route.path === '/shop' }">SHOP</span>
                </router-link>
              </li>
              <li>
                <router-link to="/blog" class="nav-link">
                  <span :class="{ 'nav-bg': true, 'active': $route.path === '/blog' }">BLOG</span>
                </router-link>
              </li>
              <li>
                <router-link to="/contact" class="nav-link">
                  <span :class="{ 'nav-bg': true, 'active': $route.path === '/contact' }">CONTACT</span>
                </router-link>
              </li>
            </ul>
          </nav>

          <div class="header-actions" :class="{ 'search-active': isSearchActive }">
            <div class="search-container" :class="{ 'active': isSearchActive }">
              <input 
                type="text" 
                class="search-input"
                placeholder="Rechercher..."
                v-model="searchQuery"
                ref="searchInput"
              >
              <button class="icon-button search-button" @click="toggleSearch">
                <i class="fas fa-search"></i>
              </button>
            </div>
            <div class="other-actions">
              <button class="icon-button">
                <a href="/wishlist"><i class="fas fa-heart"></i></a>
              </button>
              <button class="icon-button cart" >
                <a href="/cart"><i class="fas fa-shopping-cart"></i></a>
                <span class="cart-count">0</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const isSearchActive = ref(false)
const searchQuery = ref('')
const searchInput = ref(null)

const toggleSearch = () => {
  isSearchActive.value = !isSearchActive.value
  if (isSearchActive.value) {
    // Focus l'input quand la barre s'ouvre
    setTimeout(() => {
      searchInput.value?.focus()
    }, 100)
  }
}

// Ferme la barre de recherche quand on clique ailleurs
const handleClickOutside = (event) => {
  const searchContainer = document.querySelector('.search-container')
  if (isSearchActive.value && searchContainer && !searchContainer.contains(event.target)) {
    isSearchActive.value = false
  }
}

onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})

onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside)
})
</script>

<style scoped>
.top-bar {
  background-color: var(--color-primary);
  color: var(--color-light);
  padding: 10px 0;
  font-size: 14px;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 15px;
}

.top-bar-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.top-bar-contact {
  display: flex;
  gap: 20px;
}
p {
    font-family: 'Tangerine  ', serif;
    font-size: 16px; /* Vous pouvez ajuster la taille selon vos besoins */
}

.top-bar-contact a {
  color: var(--color-light);
  text-decoration: none;
  display: flex;
  align-items: center;
  gap: 8px;
}

.top-bar-contact i {
  font-size: 12px;
}

.main-header {
  background: var(--color-light);
  padding: 20px 0;
  border-bottom: 1px solid var(--color-secondary);
}

.header-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.logo img {
  height: 100px;
}

.main-nav ul {
  display: flex;
  list-style: none;
  gap: 30px;
  margin: 0;
  padding: 0;
}

.main-nav a {
  text-decoration: none;
  color: #2C1810;
  font-weight: 600;
  font-size: 15px;
  transition: color 0.3s;
}

.main-nav .active a {
  position: relative;
}

.nav-bg {
  padding: 8px 15px;
  border-radius: 20px;
  transition: all 0.3s;
}

.nav-bg.active {
  background-color: var(--color-primary);
  color: var(--color-light);
}

.nav-bg:not(.active) {
  background-color: var(--color-secondary);
  color: var(--color-dark);
}

.nav-bg:hover {
  background-color: var(--color-primary);
  color: var(--color-light);
}

.header-actions {
  display: flex;
  gap: 15px;
  align-items: center;
  transition: all 0.3s ease;
}

.other-actions {
  display: flex;
  gap: 15px;
  transition: transform 0.3s ease;
}

.icon-button {
  background: none;
  border: none;
  padding: 8px;
  cursor: pointer;
  position: relative;
  color: #2C1810;
}

.icon-button i {
  font-size: 18px;
}

.cart-count {
  position: absolute;
  top: 0;
  right: 0;
  background: var(--color-accent);
  color: white;
  font-size: 12px;
  width: 18px;
  height: 18px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  transform: translate(50%, -50%);
}

@media (max-width: 992px) {
  .top-bar-content {
    flex-direction: column;
    gap: 10px;
    text-align: center;
  }

  .top-bar-contact {
    justify-content: center;
  }

  .main-nav {
    display: none;
  }
}

@media (max-width: 576px) {
  .top-bar-contact {
    flex-direction: column;
    gap: 5px;
  }
}

.nav-link {
  text-decoration: none;
  color: #2C1810;
  font-weight: 600;
  font-size: 15px;
  transition: color 0.3s;
}

.search-container {
  position: relative;
  display: flex;
  align-items: center;
  transition: all 0.3s ease;
}

.search-input {
  width: 0;
  padding: 8px 35px 8px 15px;
  border: none;
  border-radius: 20px;
  background: var(--color-secondary);
  font-size: 16px;
  transition: all 0.3s ease;
  opacity: 0;
}

.search-container.active .search-input {
  width: 250px;
  opacity: 1;
}

.search-button {
  position: absolute;
  right: 5px;
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 8px;
  color: var(--color-dark);
  transition: all 0.3s ease;
}

.search-container.active .search-button {
  color: var(--color-primary);
}

.search-input:focus {
  outline: none;
  background: var(--color-light);
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

/* Animation pour décaler les autres icônes */
.header-actions.search-active .other-actions {
  transform: translateX(20px);
}

/* Responsive */
@media (max-width: 1200px) {
  .search-container.active .search-input {
    width: 200px;
  }
}

@media (max-width: 992px) {
  .header-actions {
    margin-left: auto;
  }
  
  .search-container.active .search-input {
    width: 150px;
  }
}

@media (max-width: 768px) {
  .search-container.active .search-input {
    width: 120px;
  }
  
  .header-actions.search-active .other-actions {
    transform: translateX(10px);
  }
}

.boulangerie-text {
    font-family: 'Lora', serif;
    font-size: 18px; /* Taille plus grande pour mettre en évidence */
}
</style> 