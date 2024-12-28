<template>
  <div class="crud-page">
    <PageHero title="Administration" />
    <div class="container">
      <!-- Onglets de navigation -->
      <div class="tabs">
        <button 
          v-for="tab in tabs" 
          :key="tab.id"
          :class="['tab-btn', { active: activeTab === tab.id }]"
          @click="activeTab = tab.id"
        >
          <i :class="tab.icon"></i>
          {{ tab.name }}
        </button>
      </div>

      <!-- Contenu des onglets -->
      <div class="tab-content">
        <!-- Gestion des Produits -->
        <div v-if="activeTab === 'products'" class="crud-section">
          <div class="section-header">
            <h2>Gestion des Produits</h2>
            <button class="add-btn" @click="openProductModal()">
              <i class="fas fa-plus"></i> Nouveau Produit
            </button>
          </div>

          <div class="table-container">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Nom</th>
                  <th>Type</th>
                  <th>Prix Revient</th>
                  <th>Prix Vente</th>
                  <th>Stock</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="product in products" :key="product.idProduit">
                  <td>{{ product.idProduit }}</td>
                  <td>{{ product.nomProduit }}</td>
                  <td>{{ product.typeProduit }}</td>
                  <td>{{ product.prixRevient }}€</td>
                  <td>{{ product.prixVente }}€</td>
                  <td>{{ product.quantiteStock }}</td>
                  <td class="actions">
                    <button class="edit-btn" @click="openProductModal(product)">
                      <i class="fas fa-edit"></i>
                    </button>
                    <button class="delete-btn" @click="deleteProduct(product.idProduit)">
                      <i class="fas fa-trash"></i>
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- Liste des Commandes -->
        <div v-if="activeTab === 'orders'" class="crud-section">
          <div class="section-header">
            <h2>Liste des Commandes</h2>
            <div class="filters">
              <input 
                type="date" 
                v-model="orderFilters.date"
                class="filter-input"
              >
              <select v-model="orderFilters.status" class="filter-input">
                <option value="">Tous les statuts</option>
                <option value="pending">En attente</option>
                <option value="processing">En cours</option>
                <option value="completed">Terminée</option>
              </select>
            </div>
          </div>

          <div class="table-container">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Date</th>
                  <th>Client</th>
                  <th>Total</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="order in filteredOrders" :key="order.idCommande">
                  <td>{{ order.idCommande }}</td>
                  <td>{{ formatDate(order.dateCommande) }}</td>
                  <td>{{ order.client }}</td>
                  <td>{{ order.total }}€</td>
                  <td>
                    <span :class="['status', order.status]">
                      {{ order.status }}
                    </span>
                  </td>
                  <td class="actions">
                    <button class="view-btn" @click="viewOrderDetails(order)">
                      <i class="fas fa-eye"></i>
                    </button>
                    <button class="edit-btn" @click="updateOrderStatus(order)">
                      <i class="fas fa-edit"></i>
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- Évaluations -->
        <div v-if="activeTab === 'reviews'" class="crud-section">
          <div class="section-header">
            <h2>Évaluations Clients</h2>
            <div class="filters">
              <select v-model="reviewFilters.rating" class="filter-input">
                <option value="">Toutes les notes</option>
                <option v-for="n in 5" :key="n" :value="n">{{ n }} étoiles</option>
              </select>
            </div>
          </div>

          <div class="reviews-grid">
            <div v-for="review in filteredReviews" :key="review.id" class="review-card">
              <div class="review-header">
                <div class="stars">
                  <i 
                    v-for="n in 5" 
                    :key="n"
                    class="fas fa-star"
                    :class="{ filled: n <= review.rating }"
                  ></i>
                </div>
                <button class="delete-btn" @click="deleteReview(review.id)">
                  <i class="fas fa-times"></i>
                </button>
              </div>
              <h3>{{ review.productName }}</h3>
              <p class="review-text">{{ review.comment }}</p>
              <div class="review-footer">
                <span class="review-author">{{ review.client }}</span>
                <span class="review-date">{{ formatDate(review.date) }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal Produit -->
    <div v-if="showProductModal" class="modal">
      <div class="modal-content">
        <div class="modal-header">
          <h2>{{ editingProduct ? 'Modifier' : 'Nouveau' }} Produit</h2>
          <button class="close-btn" @click="closeProductModal">
            <i class="fas fa-times"></i>
          </button>
        </div>
        <form @submit.prevent="saveProduct" class="product-form">
          <div class="form-group">
            <label>Nom du produit</label>
            <input type="text" v-model="productForm.nomProduit" required>
          </div>
          <div class="form-group">
            <label>Type de produit</label>
            <select v-model="productForm.idTypeProduit" required>
              <option v-for="type in productTypes" :key="type.id" :value="type.id">
                {{ type.name }}
              </option>
            </select>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label>Prix de revient</label>
              <input type="number" v-model="productForm.prixRevient" step="0.01" required>
            </div>
            <div class="form-group">
              <label>Prix de vente</label>
              <input type="number" v-model="productForm.prixVente" step="0.01" required>
            </div>
          </div>
          <div class="form-actions">
            <button type="button" class="cancel-btn" @click="closeProductModal">
              Annuler
            </button>
            <button type="submit" class="save-btn">
              {{ editingProduct ? 'Modifier' : 'Créer' }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import PageHero from '@/components/PageHero.vue'

// Configuration des onglets
const tabs = [
  { id: 'products', name: 'Produits', icon: 'fas fa-bread-slice' },
  { id: 'orders', name: 'Commandes', icon: 'fas fa-shopping-cart' },
  { id: 'reviews', name: 'Évaluations', icon: 'fas fa-star' }
]

const activeTab = ref('products')

// Gestion des produits
const products = ref([
  {
    idProduit: 1,
    nomProduit: 'Baguette Tradition',
    typeProduit: 'Pain',
    prixRevient: 0.40,
    prixVente: 1.20,
    quantiteStock: 50
  }
  // ... autres produits
])

const productTypes = [
  { id: 1, name: 'Pains Traditionnels' },
  { id: 2, name: 'Viennoiseries' },
  { id: 3, name: 'Pâtisseries' }
  // ... autres types
]

// Gestion des commandes
const orders = ref([
  {
    idCommande: 1,
    dateCommande: '2024-03-15',
    client: 'Jean Dupont',
    total: 25.80,
    status: 'pending'
  }
  // ... autres commandes
])

const orderFilters = ref({
  date: '',
  status: ''
})

// Gestion des évaluations
const reviews = ref([
  {
    id: 1,
    productName: 'Croissant au Beurre',
    rating: 5,
    comment: 'Excellent, comme toujours !',
    client: 'Marie L.',
    date: '2024-03-14'
  }
  // ... autres évaluations
])

const reviewFilters = ref({
  rating: ''
})

// Filtres
const filteredOrders = computed(() => {
  return orders.value.filter(order => {
    if (orderFilters.value.date && !order.dateCommande.includes(orderFilters.value.date)) return false
    if (orderFilters.value.status && order.status !== orderFilters.value.status) return false
    return true
  })
})

const filteredReviews = computed(() => {
  return reviews.value.filter(review => {
    if (reviewFilters.value.rating && review.rating !== parseInt(reviewFilters.value.rating)) return false
    return true
  })
})

// Modal produit
const showProductModal = ref(false)
const editingProduct = ref(null)
const productForm = ref({
  nomProduit: '',
  idTypeProduit: '',
  prixRevient: '',
  prixVente: ''
})

const openProductModal = (product = null) => {
  editingProduct.value = product
  if (product) {
    productForm.value = { ...product }
  } else {
    productForm.value = {
      nomProduit: '',
      idTypeProduit: '',
      prixRevient: '',
      prixVente: ''
    }
  }
  showProductModal.value = true
}

const closeProductModal = () => {
  showProductModal.value = false
  editingProduct.value = null
}

// Fonctions CRUD
const saveProduct = async () => {
  try {
    if (editingProduct.value) {
      // Appel API PUT pour modifier
      console.log('Modification produit:', productForm.value)
    } else {
      // Appel API POST pour créer
      console.log('Création produit:', productForm.value)
    }
    closeProductModal()
  } catch (error) {
    console.error('Erreur:', error)
  }
}

const deleteProduct = async (id) => {
  if (confirm('Êtes-vous sûr de vouloir supprimer ce produit ?')) {
    try {
      // Appel API DELETE
      console.log('Suppression produit:', id)
    } catch (error) {
      console.error('Erreur:', error)
    }
  }
}

const viewOrderDetails = (order) => {
  console.log('Détails commande:', order)
}

const updateOrderStatus = (order) => {
  console.log('Mise à jour status:', order)
}

const deleteReview = async (id) => {
  if (confirm('Êtes-vous sûr de vouloir supprimer cette évaluation ?')) {
    try {
      // Appel API DELETE
      console.log('Suppression évaluation:', id)
    } catch (error) {
      console.error('Erreur:', error)
    }
  }
}

// Utilitaires
const formatDate = (date) => {
  return new Date(date).toLocaleDateString('fr-FR')
}
</script>

<style scoped>
.crud-page {
  min-height: 100vh;
  background-color: var(--color-light);
}

.container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 40px 20px;
}

/* Styles des onglets */
.tabs {
  display: flex;
  gap: 10px;
  margin-bottom: 30px;
  background: white;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.tab-btn {
  padding: 12px 25px;
  border: none;
  background: var(--color-secondary);
  color: var(--color-dark);
  border-radius: 25px;
  cursor: pointer;
  transition: all 0.3s;
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 1em;
}

.tab-btn i {
  font-size: 1.2em;
}

.tab-btn.active {
  background: var(--color-primary);
  color: white;
}

/* Section header */
.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.section-header h2 {
  color: var(--color-dark);
  font-size: 1.8em;
}

.add-btn {
  padding: 12px 25px;
  background: var(--color-primary);
  color: white;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 1em;
  transition: all 0.3s;
}

.add-btn:hover {
  background: var(--color-accent);
  transform: translateY(-2px);
}

/* Filtres */
.filters {
  display: flex;
  gap: 15px;
}

.filter-input {
  padding: 10px 15px;
  border: 1px solid var(--color-secondary);
  border-radius: 20px;
  font-size: 0.9em;
  min-width: 150px;
}

/* Table styles */
.table-container {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th {
  background: var(--color-secondary);
  color: var(--color-dark);
  font-weight: 600;
  text-align: left;
  padding: 15px;
}

td {
  padding: 15px;
  border-bottom: 1px solid var(--color-secondary);
  color: var(--color-text);
}

tr:hover {
  background: #f8f9fa;
}

/* Actions buttons */
.actions {
  display: flex;
  gap: 8px;
  justify-content: flex-end;
}

.action-btn {
  width: 35px;
  height: 35px;
  border: none;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.3s;
}

.view-btn {
  background: var(--color-secondary);
  color: var(--color-dark);
}

.edit-btn {
  background: #3498db;
  color: white;
}

.delete-btn {
  background: #e74c3c;
  color: white;
}

.action-btn:hover {
  transform: scale(1.1);
}

/* Status badges */
.status {
  padding: 6px 12px;
  border-radius: 20px;
  font-size: 0.9em;
  font-weight: 500;
  text-align: center;
  display: inline-block;
}

.status.pending {
  background: #fff3cd;
  color: #856404;
}

.status.processing {
  background: #cce5ff;
  color: #004085;
}

.status.completed {
  background: #d4edda;
  color: #155724;
}

/* Reviews grid */
.reviews-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  margin-top: 30px;
}

.review-card {
  background: white;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.review-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.stars {
  color: #ffd700;
  display: flex;
  gap: 2px;
}

.review-text {
  color: var(--color-text);
  line-height: 1.6;
  margin: 15px 0;
}

.review-footer {
  display: flex;
  justify-content: space-between;
  color: #666;
  font-size: 0.9em;
  margin-top: 15px;
}

/* Responsive design */
@media (max-width: 992px) {
  .reviews-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .tabs {
    flex-wrap: wrap;
  }

  .tab-btn {
    flex: 1 1 calc(50% - 5px);
  }

  .section-header {
    flex-direction: column;
    gap: 15px;
  }

  .filters {
    flex-direction: column;
    width: 100%;
  }

  .filter-input {
    width: 100%;
  }

  .reviews-grid {
    grid-template-columns: 1fr;
  }

  .table-container {
    margin: 0 -20px;
    width: calc(100% + 40px);
    border-radius: 0;
  }
}

/* Animations */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.crud-section {
  animation: fadeIn 0.3s ease-out;
}

.review-card {
  transition: transform 0.3s;
}

.review-card:hover {
  transform: translateY(-5px);
}
</style> 