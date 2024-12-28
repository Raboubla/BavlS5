<template>
  <div class="blog-page">
    <PageHero title="Notre Blog" />
    <div class="container">
      <div class="blog-grid">
        <article class="blog-card" v-for="post in blogPosts" :key="post.id">
          <div class="blog-image">
            <img :src="post.image" :alt="post.title">
            <div class="blog-category">{{ post.category }}</div>
          </div>
          <div class="blog-content">
            <div class="blog-meta">
              <span><i class="fas fa-calendar"></i> {{ post.date }}</span>
              <span><i class="fas fa-user"></i> {{ post.author }}</span>
            </div>
            <h2>{{ post.title }}</h2>
            <p>{{ post.excerpt }}</p>
            <router-link :to="'/blog/' + post.id" class="read-more">
              Lire la suite <i class="fas fa-arrow-right"></i>
            </router-link>
          </div>
        </article>
      </div>
    </div>
  </div>
</template>

<script setup>
import PageHero from '@/components/PageHero.vue'

const blogPosts = [
  {
    id: 1,
    title: 'Les secrets de notre pain au levain',
    excerpt: 'Découvrez comment nous préparons notre pain au levain traditionnel...',
    image: '/images/blog-levain.jpg',
    category: 'Recettes',
    date: '15 Mars 2024',
    author: 'Chef Boulanger'
  },
  // Ajoutez plus d'articles ici
]
</script>

<style scoped>
.blog-hero {
  background-color: var(--color-primary);
  color: var(--color-light);
  padding: 60px 0;
  text-align: center;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 40px 15px;
}

.blog-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 30px;
}

.blog-card {
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.blog-image {
  position: relative;
  height: 200px;
}

.blog-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.blog-category {
  position: absolute;
  top: 15px;
  right: 15px;
  background: var(--color-accent);
  color: white;
  padding: 5px 15px;
  border-radius: 20px;
  font-size: 0.9em;
}

.blog-content {
  padding: 20px;
}

.blog-meta {
  display: flex;
  gap: 20px;
  color: #666;
  font-size: 0.9em;
  margin-bottom: 15px;
}

.blog-meta i {
  color: #1B2B5D;
  margin-right: 5px;
}

.blog-content h2 {
  color: #1B2B5D;
  margin-bottom: 10px;
  font-size: 1.5em;
}

.blog-content p {
  color: #666;
  margin-bottom: 20px;
  line-height: 1.6;
}

.read-more {
  color: #1B2B5D;
  text-decoration: none;
  font-weight: 500;
  display: inline-flex;
  align-items: center;
  gap: 5px;
  transition: color 0.3s;
}

.read-more:hover {
  color: var(--color-accent);
}

@media (max-width: 768px) {
  .blog-grid {
    grid-template-columns: 1fr;
  }
}
</style> 