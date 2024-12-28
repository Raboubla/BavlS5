<template>
  <div class="auth-page">
    <div class="auth-container">
      <div class="auth-section">
        <div class="auth-header">
          <img src="../assets/bakery-icon.png" alt="Inscription" class="auth-logo">
          <h2>Créer un compte</h2>
        </div>
        <form @submit.prevent="handleSignup" class="auth-form">
          <div class="form-group">
            <label for="nomClient">Nom complet</label>
            <div class="input-with-icon">
              <i class="fas fa-user"></i>
              <input 
                type="text" 
                id="nomClient" 
                v-model="signupForm.nomClient" 
                required
                placeholder="Votre nom complet"
              >
            </div>
          </div>

          <div class="form-group">
            <label for="email">Email</label>
            <div class="input-with-icon">
              <i class="fas fa-envelope"></i>
              <input 
                type="email" 
                id="email" 
                v-model="signupForm.email" 
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
                v-model="signupForm.password" 
                required
                placeholder="Votre mot de passe"
              >
            </div>
          </div>

          <div class="form-group">
            <label for="telephone">Téléphone</label>
            <div class="input-with-icon">
              <i class="fas fa-phone"></i>
              <input 
                type="tel" 
                id="telephone" 
                v-model="signupForm.telephone" 
                required
                placeholder="Votre numéro de téléphone"
              >
            </div>
          </div>

          <div class="form-group">
            <label for="adresse">Adresse</label>
            <div class="input-with-icon">
              <i class="fas fa-home"></i>
              <input 
                type="text" 
                id="adresse" 
                v-model="signupForm.adresse" 
                required
                placeholder="Votre adresse complète"
              >
            </div>
          </div>

          <div class="form-group checkbox">
            <input 
              type="checkbox" 
              id="terms" 
              v-model="signupForm.terms" 
              required
            >
            <label for="terms">
              J'accepte les conditions générales d'utilisation
            </label>
          </div>

          <button type="submit" class="btn-primary">
            <i class="fas fa-user-plus"></i>
            Créer mon compte
          </button>
        </form>
        <div class="auth-footer">
          <p>Déjà un compte ? <router-link to="/login">Se connecter</router-link></p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const signupForm = reactive({
  nomClient: '',
  email: '',
  password: '',
  telephone: '',
  adresse: '',
  terms: false
})

const handleSignup = async () => {
  try {
    if (!signupForm.terms) {
      alert('Veuillez accepter les conditions générales')
      return
    }

    // Préparer les données pour l'API
    const userData = {
      nomClient: signupForm.nomClient,
      email: signupForm.email,
      password: signupForm.password,
      telephone: signupForm.telephone,
      adresse: signupForm.adresse
    }

    // Appel API pour créer le compte
    // const response = await axios.post('/api/register', userData)
    console.log('Données d\'inscription:', userData)
    
    // Si l'inscription réussit, rediriger vers la connexion
    router.push('/login')
  } catch (error) {
    console.error('Erreur lors de l\'inscription:', error)
    alert('Une erreur est survenue lors de l\'inscription')
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
  max-width: 500px;
  margin: 0 auto;
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
  justify-content: center;
  gap: 10px;
}

.btn-primary:hover {
  background-color: var(--color-accent);
  transform: translateY(-2px);
}

.auth-footer {
  text-align: center;
  margin-top: 20px;
  padding-top: 20px;
  border-top: 1px solid var(--color-secondary);
}

.auth-footer a {
  color: var(--color-primary);
  text-decoration: none;
  font-weight: 500;
  transition: color 0.3s;
}

.auth-footer a:hover {
  color: var(--color-accent);
}

@media (max-width: 576px) {
  .auth-page {
    padding: 20px;
  }

  .auth-section {
    padding: 20px;
  }

  .btn-primary {
    width: 100%;
  }
}
</style> 