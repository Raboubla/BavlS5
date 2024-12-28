<template>
  <div class="auth-page">
    <div class="auth-container">
      <div class="auth-section">
        <div class="auth-header">
          <img src="../assets/bread-icon.png" alt="Logo" class="auth-logo">
          <h2>Connexion</h2>
        </div>
        <form @submit.prevent="handleLogin" class="auth-form">
          <div class="form-group">
            <label for="email">Email</label>
            <div class="input-with-icon">
              <i class="fas fa-envelope"></i>
              <input 
                type="email" 
                id="email" 
                v-model="loginForm.email" 
                required
                placeholder="Votre email"
              >
            </div>
          </div>
          <div class="form-group">
            <label for="password">Mot de passe</label>
            <div class="input-with-icon">
              <i class="fas fa-lock"></i>
              <input 
                type="password" 
                id="password" 
                v-model="loginForm.password" 
                required
                placeholder="Votre mot de passe"
              >
            </div>
          </div>
          <div class="form-actions">
            <button type="submit" class="btn-primary">
              <i class="fas fa-sign-in-alt"></i>
              Se connecter
            </button>
            <router-link to="/forgot-password" class="forgot-password">
              Mot de passe oublié ?
            </router-link>
          </div>
        </form>
        <div class="auth-footer">
          <p>Pas encore de compte ? <router-link to="/register">S'inscrire</router-link></p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const authStore = useAuthStore()

const loginForm = reactive({
  email: '',
  password: ''
})

const handleLogin = async () => {
  const success = await authStore.login(loginForm)
  if (success) {
    router.push('/')
  }
}
</script>

<style scoped>
.auth-page {
  min-height: 100vh;
  background-image: linear-gradient(rgba(44, 24, 16, 0.8), rgba(44, 24, 16, 0.8)), url('../assets/bakery-bg.jpg');
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
  padding: 60px 20px;
}

.auth-container {
  max-width: 1200px;
  margin: 0 auto;
}

.auth-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 40px;
  margin-bottom: 40px;
}

.auth-section {
  background: rgba(255, 255, 255, 0.95);
  padding: 40px;
  border-radius: 15px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.2);
}

.auth-header {
  text-align: center;
  margin-bottom: 30px;
}

.auth-logo {
  width: 80px;
  height: 80px;
  margin-bottom: 20px;
}

h2 {
  color: var(--color-primary);
  font-size: 1.8em;
  margin-bottom: 10px;
  font-family: 'Playfair Display', serif;
}

.auth-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-group label {
  color: var(--color-dark);
  font-weight: 500;
}

.input-with-icon {
  position: relative;
}

.input-with-icon i {
  position: absolute;
  left: 15px;
  top: 50%;
  transform: translateY(-50%);
  color: var(--color-primary);
}

.input-with-icon input {
  width: 100%;
  padding: 12px 12px 12px 45px;
  border: 2px solid var(--color-secondary);
  border-radius: 8px;
  font-size: 16px;
  transition: all 0.3s;
}

.input-with-icon input:focus {
  border-color: var(--color-primary);
  outline: none;
  box-shadow: 0 0 0 3px rgba(139, 69, 19, 0.1);
}

.checkbox {
  flex-direction: row;
  align-items: center;
  gap: 12px;
}

.checkbox label {
  font-size: 0.9em;
  font-weight: normal;
}

.form-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.forgot-password {
  color: var(--color-primary);
  text-decoration: none;
  font-size: 0.9em;
  transition: color 0.3s;
}

.forgot-password:hover {
  color: var(--color-accent);
}

.btn-primary {
  padding: 12px 25px;
  background-color: var(--color-primary);
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
  transition: all 0.3s;
  display: flex;
  align-items: center;
  gap: 10px;
}

.btn-primary:hover {
  background-color: var(--color-accent);
  transform: translateY(-2px);
}

.guest-section {
  background: rgba(255, 255, 255, 0.95);
  padding: 40px;
  border-radius: 15px;
  text-align: center;
  box-shadow: 0 4px 20px rgba(0,0,0,0.2);
}

.guest-content {
  max-width: 500px;
  margin: 0 auto;
}

.guest-content i {
  font-size: 3em;
  color: var(--color-primary);
  margin-bottom: 20px;
}

.btn-secondary {
  display: inline-flex;
  align-items: center;
  gap: 10px;
  padding: 12px 25px;
  background-color: var(--color-secondary);
  color: var(--color-dark);
  text-decoration: none;
  border-radius: 8px;
  font-size: 16px;
  transition: all 0.3s;
  margin-top: 20px;
}

.btn-secondary:hover {
  background-color: var(--color-primary);
  color: white;
  transform: translateY(-2px);
}

@media (max-width: 992px) {
  .auth-grid {
    grid-template-columns: 1fr;
  }

  .auth-section {
    padding: 30px;
  }
}

@media (max-width: 576px) {
  .auth-page {
    padding: 20px;
  }

  .form-actions {
    flex-direction: column;
    gap: 15px;
  }

  .btn-primary {
    width: 100%;
    justify-content: center;
  }
}
</style> 