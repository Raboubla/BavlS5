<template>
  <div class="cart-page">
    <div class="cart-hero">
      <h1>Mon Panier</h1>
    </div>
    
    <div class="container">
      <div class="cart-content" v-if="cartItems.length > 0">
        <div class="cart-items">
          <div class="cart-item" v-for="item in cartItems" :key="item.id">
            <div class="item-image">
              <img :src="item.image" :alt="item.name">
            </div>
            <div class="item-details">
              <h3>{{ item.name }}</h3>
              <p class="item-description">{{ item.description }}</p>
            </div>
            <div class="item-quantity">
              <button class="quantity-btn" @click="decrementQuantity(item)">
                <i class="fas fa-minus"></i>
              </button>
              <span class="quantity">{{ item.quantity }}</span>
              <button class="quantity-btn" @click="incrementQuantity(item)">
                <i class="fas fa-plus"></i>
              </button>
            </div>
            <div class="item-price">{{ (item.price * item.quantity).toFixed(2) }}€</div>
            <button class="remove-btn" @click="removeItem(item)">
              <i class="fas fa-trash"></i>
            </button>
          </div>
        </div>

        <div class="cart-summary">
          <h2>Résumé de la commande</h2>
          <div class="summary-line">
            <span>Sous-total</span>
            <span>{{ subtotal.toFixed(2) }}€</span>
          </div>
          <div class="summary-line">
            <span>Frais de livraison</span>
            <span>{{ shipping.toFixed(2) }}€</span>
          </div>
          <div class="summary-line total">
            <span>Total</span>
            <span>{{ total.toFixed(2) }}€</span>
          </div>
          <button class="checkout-btn" @click="checkout">
            Commander maintenant
          </button>
          <div class="delivery-info">
            <i class="fas fa-truck"></i>
            <p>Livraison gratuite à partir de 30€</p>
          </div>
        </div>
      </div>

      <div class="empty-cart" v-else>
        <i class="fas fa-shopping-basket"></i>
        <h2>Votre panier est vide</h2>
        <p>Découvrez nos délicieux produits et remplissez votre panier</p>
        <router-link to="/shop" class="continue-shopping">
          Continuer mes achats
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// Exemple de données (à remplacer par vos données réelles)
const cartItems = ref([
  {
    id: 1,
    name: 'Baguette Tradition',
    description: 'La baguette authentique à la française',
    price: 1.20,
    quantity: 2,
    image: 'https://images.unsplash.com/photo-1608198093002-ad4e005484ec?w=800'
  },
  {
    id: 2,
    name: 'Croissant au Beurre',
    description: 'Pur beurre, croustillant à souhait',
    price: 1.50,
    quantity: 3,
    image: 'https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=800'
  }
])

const shipping = ref(2.99)

const subtotal = computed(() => {
  return cartItems.value.reduce((sum, item) => sum + (item.price * item.quantity), 0)
})

const total = computed(() => {
  return subtotal.value >= 30 ? subtotal.value : subtotal.value + shipping.value
})

const incrementQuantity = (item) => {
  item.quantity++
}

const decrementQuantity = (item) => {
  if (item.quantity > 1) {
    item.quantity--
  }
}

const removeItem = (itemToRemove) => {
  cartItems.value = cartItems.value.filter(item => item.id !== itemToRemove.id)
}

const checkout = () => {
  console.log('Procéder au paiement')
  // Ajoutez ici votre logique de paiement
}
</script>

<style scoped>
.cart-page {
  background-color: var(--color-light);
}

.cart-hero {
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

.cart-hero h1 {
  color: white;
  font-size: 3em;
  text-align: center;
  margin: 0;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.cart-content {
  background: rgba(255, 255, 255, 0.95);
  border-radius: 8px;
  padding: 30px;
  margin-bottom: 60px;
  display: grid;
  grid-template-columns: 1fr 350px;
  gap: 30px;
}

.cart-items {
  background: white;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.cart-item {
  display: grid;
  grid-template-columns: 100px 1fr auto auto auto;
  gap: 20px;
  align-items: center;
  padding: 20px;
  border-bottom: 1px solid var(--color-secondary);
}

.cart-item:last-child {
  border-bottom: none;
}

.item-image {
  width: 100px;
  height: 100px;
  overflow: hidden;
  border-radius: 8px;
}

.item-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.item-details h3 {
  color: var(--color-primary);
  margin-bottom: 8px;
}

.item-description {
  color: var(--color-text);
  font-size: 0.9em;
}

.item-quantity {
  display: flex;
  align-items: center;
  gap: 10px;
}

.quantity-btn {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  border: none;
  background: var(--color-secondary);
  color: var(--color-dark);
  cursor: pointer;
  transition: all 0.3s;
}

.quantity-btn:hover {
  background: var(--color-primary);
  color: white;
}

.quantity {
  font-weight: 600;
  min-width: 30px;
  text-align: center;
}

.item-price {
  font-weight: 600;
  color: var(--color-primary);
  min-width: 80px;
  text-align: right;
}

.remove-btn {
  color: var(--color-accent);
  background: none;
  border: none;
  cursor: pointer;
  padding: 8px;
  transition: color 0.3s;
}

.remove-btn:hover {
  color: darkred;
}

.cart-summary {
  background: white;
  border-radius: 8px;
  padding: 30px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  height: fit-content;
}

.cart-summary h2 {
  color: var(--color-primary);
  margin-bottom: 20px;
}

.summary-line {
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
  color: var(--color-text);
}

.total {
  font-weight: 600;
  font-size: 1.2em;
  color: var(--color-primary);
  border-top: 1px solid var(--color-secondary);
  padding-top: 15px;
  margin-top: 15px;
}

.checkout-btn {
  width: 100%;
  padding: 15px;
  background: var(--color-primary);
  color: white;
  border: none;
  border-radius: 25px;
  font-size: 1.1em;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-top: 20px;
}

.checkout-btn:hover {
  background: var(--color-accent);
}

.delivery-info {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-top: 20px;
  color: var(--color-text);
  font-size: 0.9em;
}

.empty-cart {
  text-align: center;
  padding: 60px 0;
}

.empty-cart i {
  font-size: 4em;
  color: var(--color-primary);
  margin-bottom: 20px;
}

.empty-cart h2 {
  color: var(--color-primary);
  margin-bottom: 15px;
}

.continue-shopping {
  display: inline-block;
  margin-top: 20px;
  padding: 12px 25px;
  background: var(--color-primary);
  color: white;
  text-decoration: none;
  border-radius: 25px;
  transition: background-color 0.3s;
}

.continue-shopping:hover {
  background: var(--color-accent);
}

@media (max-width: 992px) {
  .cart-content {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .cart-hero {
    height: 200px;
  }

  .cart-hero h1 {
    font-size: 2em;
  }

  .cart-item {
    grid-template-columns: 80px 1fr;
    gap: 15px;
  }

  .item-quantity, .item-price {
    grid-column: 2;
  }

  .remove-btn {
    position: absolute;
    top: 10px;
    right: 10px;
  }

  .cart-item {
    position: relative;
  }
}
</style> 