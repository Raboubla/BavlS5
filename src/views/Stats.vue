<template>
  <div class="stats-page">
    <PageHero title="Statistiques" />
    <div class="container">
      <!-- Résumé des ventes -->
      <div class="stats-grid">
        <div class="stat-card">
          <i class="fas fa-shopping-cart"></i>
          <div class="stat-content">
            <h3>Commandes Aujourd'hui</h3>
            <p class="stat-number">{{ todayStats.NombreCommandes }}</p>
            <p class="stat-revenue">{{ todayStats.RevenuTotal.toFixed(2) }}€</p>
          </div>
        </div>

        <div class="stat-card">
          <i class="fas fa-chart-line"></i>
          <div class="stat-content">
            <h3>Ventes ce mois</h3>
            <p class="stat-number">{{ monthStats.TotalProduitsVendus }}</p>
            <p class="stat-revenue">{{ monthStats.RevenuTotal.toFixed(2) }}€</p>
          </div>
        </div>

        <div class="stat-card">
          <i class="fas fa-star"></i>
          <div class="stat-content">
            <h3>Meilleur Produit</h3>
            <p class="stat-product">{{ topProduct.nomProduit }}</p>
            <p class="stat-sold">{{ topProduct.QuantiteVendue }} vendus</p>
          </div>
        </div>
      </div>

      <!-- Graphique des ventes -->
      <div class="chart-section">
        <div class="chart-header">
          <h2>Évolution des Ventes</h2>
          <div class="chart-filters">
            <button 
              v-for="period in periods" 
              :key="period.value"
              :class="['filter-btn', { active: currentPeriod === period.value }]"
              @click="changePeriod(period.value)"
            >
              {{ period.label }}
            </button>
          </div>
        </div>
        <div class="chart-container">
          <canvas ref="salesChart"></canvas>
        </div>
      </div>

      <!-- Top Produits -->
      <div class="products-performance">
        <h2>Performance des Produits</h2>
        <div class="table-container">
          <table>
            <thead>
              <tr>
                <th>Produit</th>
                <th>Quantité Vendue</th>
                <th>Revenu</th>
                <th>Coût Production</th>
                <th>Marge</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="product in productPerformance" :key="product.nomProduit">
                <td>{{ product.nomProduit }}</td>
                <td>{{ product.QuantiteVendue }}</td>
                <td>{{ product.Revenu.toFixed(2) }}€</td>
                <td>{{ product.CoutProduction.toFixed(2) }}€</td>
                <td :class="{ 'positive': product.Marge > 0, 'negative': product.Marge < 0 }">
                  {{ product.Marge.toFixed(2) }}€
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <!-- Tendances -->
      <div class="trends-section">
        <h2>Tendances des Commandes</h2>
        <div class="trends-chart">
          <canvas ref="trendsChart"></canvas>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import PageHero from '@/components/PageHero.vue'
import Chart from 'chart.js/auto'

// Périodes disponibles pour le filtrage
const periods = [
  { value: 'day', label: 'Jour' },
  { value: 'week', label: 'Semaine' },
  { value: 'month', label: 'Mois' },
  { value: 'year', label: 'Année' }
]

const currentPeriod = ref('month')
const salesChart = ref(null)
const trendsChart = ref(null)

// Données simulées (à remplacer par les vraies données de l'API)
const todayStats = ref({
  NombreCommandes: 45,
  RevenuTotal: 1250.50,
  TotalProduitsVendus: 180
})

const monthStats = ref({
  NombreCommandes: 1200,
  RevenuTotal: 35000,
  TotalProduitsVendus: 4500
})

const topProduct = ref({
  nomProduit: 'Croissant au Beurre',
  QuantiteVendue: 850,
  Revenu: 1275
})

const productPerformance = ref([
  {
    nomProduit: 'Croissant au Beurre',
    QuantiteVendue: 850,
    Revenu: 1275,
    CoutProduction: 425,
    Marge: 850
  },
  // ... autres produits
])

// Fonction pour changer la période d'affichage
const changePeriod = (period) => {
  currentPeriod.value = period
  updateCharts()
}

// Initialisation des graphiques
onMounted(() => {
  initSalesChart()
  initTrendsChart()
})

const initSalesChart = () => {
  const ctx = salesChart.value.getContext('2d')
  new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Juin'],
      datasets: [{
        label: 'Ventes',
        data: [12000, 19000, 15000, 25000, 22000, 30000],
        borderColor: '#8B4513',
        tension: 0.4
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false
    }
  })
}

const initTrendsChart = () => {
  const ctx = trendsChart.value.getContext('2d')
  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam', 'Dim'],
      datasets: [{
        label: 'Commandes',
        data: [65, 59, 80, 81, 56, 95, 40],
        backgroundColor: '#8B4513'
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false
    }
  })
}
</script>

<style scoped>
.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 30px;
  margin-bottom: 40px;
}

.stat-card {
  background: white;
  padding: 25px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  display: flex;
  align-items: center;
  gap: 20px;
}

.stat-card i {
  font-size: 2.5em;
  color: var(--color-primary);
}

.stat-content h3 {
  color: var(--color-dark);
  margin-bottom: 10px;
  font-size: 1.1em;
}

.stat-number {
  font-size: 2em;
  font-weight: bold;
  color: var(--color-primary);
}

.stat-revenue {
  color: var(--color-accent);
  font-weight: 500;
}

.chart-section {
  background: white;
  padding: 30px;
  border-radius: 8px;
  margin-bottom: 40px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.chart-filters {
  display: flex;
  gap: 10px;
}

.filter-btn {
  padding: 8px 15px;
  border: none;
  background: var(--color-secondary);
  color: var(--color-dark);
  border-radius: 20px;
  cursor: pointer;
  transition: all 0.3s;
}

.filter-btn.active {
  background: var(--color-primary);
  color: white;
}

.chart-container {
  height: 400px;
}

.table-container {
  background: white;
  padding: 30px;
  border-radius: 8px;
  margin-bottom: 40px;
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 15px;
  text-align: left;
  border-bottom: 1px solid var(--color-secondary);
}

th {
  background: var(--color-secondary);
  color: var(--color-dark);
}

.positive {
  color: #2ecc71;
}

.negative {
  color: #e74c3c;
}

.trends-section {
  background: white;
  padding: 30px;
  border-radius: 8px;
  margin-bottom: 40px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.trends-chart {
  height: 300px;
}

@media (max-width: 768px) {
  .chart-header {
    flex-direction: column;
    gap: 15px;
  }

  .chart-filters {
    width: 100%;
    overflow-x: auto;
    padding-bottom: 10px;
  }

  .table-container {
    margin: 0 -15px;
    width: calc(100% + 30px);
  }
}
</style> 