<template>
  <div class="pages-page">
    <PageHero title="Nos Pages" />
    <div class="container">
      <div class="pages-grid">
        <div class="page-card" v-for="page in pages" :key="page.id">
          <div class="page-image">
            <img :src="page.image" :alt="page.title">
            <div class="page-overlay">
              <router-link :to="page.link" class="page-link">
                <i class="fas fa-arrow-right"></i>
              </router-link>
            </div>
          </div>
          <div class="page-content">
            <h2>{{ page.title }}</h2>
            <p>{{ page.description }}</p>
            <router-link :to="page.link" class="btn-discover">
              Découvrir
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import PageHero from '@/components/PageHero.vue'

const pages = [
  {
    id: 1,
    title: 'Notre Histoire',
    description: 'Découvrez l\'histoire de notre boulangerie artisanale et nos traditions.',
    image: 'https://images.unsplash.com/photo-1509440159596-0249088772ff?w=800',
    link: '/about/history'
  },
  {
    id: 2,
    title: 'Nos Valeurs',
    description: 'Les valeurs qui nous guident au quotidien dans notre travail.',
    image: 'https://images.unsplash.com/photo-1549931319-a545dcf3bc73?w=800',
    link: '/about/values'
  },
  {
    id: 3,
    title: 'Notre Équipe',
    description: 'Rencontrez les artisans passionnés qui composent notre équipe.',
    image: 'https://images.unsplash.com/photo-1556217477-d325251ece38?w=800',
    link: '/about/team'
  },
  {
    id: 4,
    title: 'Nos Engagements',
    description: 'Nos engagements pour une boulangerie responsable et durable.',
    image: 'https://images.unsplash.com/photo-1495147466023-ac5c588e2e94?w=800',
    link: '/about/commitments'
  },
  {
    id: 5,
    title: 'Franchise',
    description: 'Rejoignez notre réseau de franchisés et développez votre activité.',
    image: 'https://images.unsplash.com/photo-1591688515527-f7b20bd05902?w=800',
    link: '/franchise'
  },
  {
    id: 6,
    title: 'Carrières',
    description: 'Rejoignez notre équipe de passionnés.',
    image: 'https://images.unsplash.com/photo-1609159085820-d9a6ff0f087c?w=800',
    link: '/careers'
  }
]
</script>

<style scoped>
.pages-hero {
  background-color: var(--color-primary);
  color: var(--color-light);
  padding: 60px 0;
  text-align: center;
}

.pages-hero h1 {
  font-size: 2.5em;
  margin-bottom: 15px;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 60px 15px;
}

.pages-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 30px;
}

.page-card {
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  transition: transform 0.3s;
  border: 1px solid var(--color-secondary);
}

.page-card:hover {
  transform: translateY(-5px);
}

.page-image {
  position: relative;
  height: 250px;
  overflow: hidden;
}

.page-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s;
}

.page-card:hover .page-image img {
  transform: scale(1.1);
}

.page-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(139, 69, 19, 0.7);
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
  transition: opacity 0.3s;
}

.page-card:hover .page-overlay {
  opacity: 1;
}

.page-link {
  width: 50px;
  height: 50px;
  background: white;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--color-primary);
  text-decoration: none;
  transition: all 0.3s;
}

.page-link:hover {
  background: var(--color-secondary);
  color: var(--color-accent);
}

.page-content {
  padding: 25px;
  text-align: center;
}

.page-content h2 {
  color: var(--color-primary);
  margin-bottom: 15px;
  font-size: 1.5em;
}

.page-content p {
  color: var(--color-text);
  margin-bottom: 20px;
  line-height: 1.6;
}

.btn-discover {
  display: inline-block;
  padding: 10px 25px;
  background-color: var(--color-primary);
  color: var(--color-light);
  text-decoration: none;
  border-radius: 25px;
  transition: background-color 0.3s;
}

.btn-discover:hover {
  background-color: var(--color-accent);
}

@media (max-width: 768px) {
  .pages-grid {
    grid-template-columns: 1fr;
  }
  
  .page-image {
    height: 200px;
  }
}

@media (max-width: 576px) {
  .pages-hero h1 {
    font-size: 2em;
  }
  
  .container {
    padding: 30px 15px;
  }
}
</style> 