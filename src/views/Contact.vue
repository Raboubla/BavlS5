<template>
  <div class="contact-page">
    <PageHero title="Nous Contacter" />
    <div class="container">
      <div class="contact-grid">
        <div class="contact-info">
          <div class="info-card">
            <i class="fas fa-map-marker-alt"></i>
            <h3>Notre adresse</h3>
            <p>123 Rue de la Boulangerie<br>75001 Paris, France</p>
          </div>
          <div class="info-card">
            <i class="fas fa-phone"></i>
            <h3>Téléphone</h3>
            <p>+33 1 23 45 67 89</p>
          </div>
          <div class="info-card">
            <i class="fas fa-envelope"></i>
            <h3>Email</h3>
            <p>contact@boulangerie.fr</p>
          </div>
          <div class="info-card">
            <i class="fas fa-clock"></i>
            <h3>Horaires</h3>
            <p>Lun-Sam: 7h-20h<br>Dim: 7h-13h</p>
          </div>
        </div>

        <form @submit.prevent="handleSubmit" class="contact-form">
          <div class="form-group">
            <label>Nom</label>
            <input type="text" v-model="form.name" required>
          </div>
          <div class="form-group">
            <label>Email</label>
            <input type="email" v-model="form.email" required>
          </div>
          <div class="form-group">
            <label>Sujet</label>
            <input type="text" v-model="form.subject" required>
          </div>
          <div class="form-group">
            <label>Message</label>
            <textarea v-model="form.message" required rows="5"></textarea>
          </div>
          <button type="submit" class="submit-btn">Envoyer le message</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import PageHero from '@/components/PageHero.vue'

const form = reactive({
  name: '',
  email: '',
  subject: '',
  message: ''
})

const handleSubmit = () => {
  console.log('Form submitted:', form)
  // Ajoutez ici la logique d'envoi du formulaire
}
</script>

<style scoped>
.contact-hero {
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

.contact-grid {
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 40px;
  margin-top: 40px;
}

.contact-info {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  background: var(--color-secondary);
  padding: 20px;
  border-radius: 8px;
  text-align: center;
}

.info-card i {
  font-size: 2em;
  color: var(--color-primary);
  margin-bottom: 15px;
}

.info-card h3 {
  color: #1B2B5D;
  margin-bottom: 10px;
}

.contact-form {
  background: white;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #1B2B5D;
  font-weight: 500;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 16px;
}

.form-group textarea {
  resize: vertical;
}

.submit-btn {
  width: 100%;
  padding: 12px;
  background: var(--color-primary);
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-btn:hover {
  background: var(--color-accent);
}

@media (max-width: 992px) {
  .contact-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .contact-info {
    grid-template-columns: 1fr;
  }
}
</style> 