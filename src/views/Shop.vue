<template>
  <div class="shop-page">
    <PageHero title="Notre Boutique" />
    <div class="container">
      <div class="filters">
        <button 
          v-for="category in categories" 
          :key="category.id"
          :class="['filter-btn', { active: activeCategory === category.id }]"
          @click="filterByCategory(category.id)"
        >
          {{ category.name }}
        </button>
      </div>

      <div class="products-grid">
        <div 
          v-for="product in filteredProducts" 
          :key="product.id" 
          class="product-card"
        >
          <div class="product-image">
            <img :src="product.image" :alt="product.name">
            <div class="product-actions">
              <button class="action-btn" @click="addToCart(product)">
                <i class="fas fa-shopping-cart"></i>
              </button>
              <button class="action-btn" @click="addToWishlist(product)">
                <i class="fas fa-heart"></i>
              </button>
            </div>
          </div>
          <div class="product-info">
            <h3>{{ product.name }}</h3>
            <div class="rating">
              <div class="stars">
                <i 
                  v-for="star in 5" 
                  :key="star"
                  :class="[
                    'fas fa-star',
                    { 
                      'filled': star <= product.rating,
                      'half-filled': star - 0.5 === product.rating
                    }
                  ]"
                ></i>
              </div>
              <span class="rating-count">({{ product.reviews }} avis)</span>
            </div>
            <p class="price">{{ product.price.toFixed(2) }} €</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import PageHero from '@/components/PageHero.vue'

const categories = [
  { id: 'all', name: 'Tout' },
  { id: 'traditional', name: 'Pains Traditionnels' },
  { id: 'viennoiseries', name: 'Viennoiseries' },
  { id: 'pastries', name: 'Pâtisseries Classiques' },
  { id: 'cookies', name: 'Biscuits et Petits Gâteaux' },
  { id: 'savory', name: 'Produits Salés' },
  { id: 'specialties', name: 'Spécialités Régionales' }
]

const products = [
  {
    id: 1,
    name: 'Baguette Tradition',
    price: 1.20,
    category: 'traditional',
    image: 'https://images.unsplash.com/photo-1597079910443-60c43fc4f729?w=800',
    rating: 4.5,
    reviews: 128
  },
  {
    id: 2,
    name: 'Pain de Campagne',
    price: 3.50,
    category: 'traditional',
    image: 'https://images.unsplash.com/photo-1589367920969-ab8e050bbb04?w=800',
    rating: 4.8,
    reviews: 96
  },
  {
    id: 3,
    name: 'Croissant au Beurre',
    price: 1.50,
    category: 'viennoiseries',
    image: 'https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=800',
    rating: 4.7,
    reviews: 203
  },
  {
    id: 4,
    name: 'Pain au Chocolat',
    price: 1.60,
    category: 'viennoiseries',
    image: 'https://images.unsplash.com/photo-1626094309830-abbb0c99da4a?w=800',
    rating: 4.6,
    reviews: 167
  },
  {
    id: 5,
    name: 'Éclair au Chocolat',
    price: 3.80,
    category: 'pastries',
    image: 'https://images.unsplash.com/photo-1634118520179-0c78b72df69a?w=800',
    rating: 4.9,
    reviews: 89
  },
  // ... Ajoutez tous les autres produits de la même manière
]

const activeCategory = ref('all')

const filteredProducts = computed(() => {
  if (activeCategory.value === 'all') {
    return products
  }
  return products.filter(product => product.category === activeCategory.value)
})

const filterByCategory = (categoryId) => {
  activeCategory.value = categoryId
}

const addToCart = (product) => {
  console.log('Ajouter au panier:', product)
  // Implémentez la logique d'ajout au panier
}

const addToWishlist = (product) => {
  console.log('Ajouter aux favoris:', product)
  // Implémentez la logique d'ajout aux favoris
}
</script>

<style scoped>
.shop-hero {
  background-color: var(--color-primary);
  color: var(--color-light);
  padding: 60px 0;
  text-align: center;
}

.shop-hero h1 {
  font-size: 2.5em;
  margin-bottom: 15px;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 40px 15px;
}

.filters {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 15px;
  margin-bottom: 40px;
}

.filter-btn {
  padding: 10px 20px;
  border: none;
  background-color: var(--color-secondary);
  color: var(--color-dark);
  border-radius: 25px;
  cursor: pointer;
  transition: all 0.3s;
  font-size: 0.9em;
}

.filter-btn.active, 
.filter-btn:hover {
  background-color: var(--color-primary);
  color: var(--color-light);
}

.products-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 30px;
}

.product-card {
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  transition: transform 0.3s;
}

.product-card:hover {
  transform: translateY(-5px);
}

.product-image {
  position: relative;
  height: 200px;
  overflow: hidden;
}

.product-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.product-actions {
  position: absolute;
  bottom: -50px;
  left: 0;
  right: 0;
  display: flex;
  justify-content: center;
  gap: 10px;
  padding: 10px;
  background: rgba(27, 43, 93, 0.9);
  transition: bottom 0.3s;
}

.product-card:hover .product-actions {
  bottom: 0;
}

.action-btn {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: none;
  background: white;
  color: #1B2B5D;
  cursor: pointer;
  transition: all 0.3s;
}

.action-btn:hover {
  background: #f5f1eb;
  color: #FF6B6B;
}

.product-info {
  padding: 20px;
  text-align: center;
}

.product-info h3 {
  color: var(--color-dark);
  margin-bottom: 8px;
  font-size: 1.1em;
  height: 2.4em;
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.price {
  color: var(--color-accent);
  font-weight: bold;
  font-size: 1.2em;
  margin-top: 8px;
}

.rating {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  margin: 8px 0;
}

.stars {
  display: flex;
  gap: 2px;
}

.stars i {
  color: #ddd;
  font-size: 14px;
}

.stars i.filled {
  color: #FFD700;
}

.stars i.half-filled {
  position: relative;
  color: #ddd;
}

.stars i.half-filled::before {
  content: '\f089'; /* Demi-étoile */
  position: absolute;
  left: 0;
  top: 0;
  width: 50%;
  overflow: hidden;
  color: #FFD700;
}

.rating-count {
  color: #666;
  font-size: 0.9em;
}

/* Animation au survol des étoiles */
.stars i {
  transition: transform 0.2s;
}

.stars:hover i {
  transform: scale(1.1);
}

@media (max-width: 768px) {
  .filters {
    flex-wrap: wrap;
  }
  
  .filter-btn {
    flex: 1 1 calc(50% - 10px);
  }

  .product-info {
    padding: 15px;
  }

  .stars i {
    font-size: 12px;
  }

  .rating-count {
    font-size: 0.8em;
  }
}
</style> 