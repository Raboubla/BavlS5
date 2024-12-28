import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null,
    isAuthenticated: false
  }),

  actions: {
    async login(credentials) {
      try {
        // Appel API login
        // const response = await axios.post('/api/login', credentials)
        
        // Simuler une réponse
        const user = { id: 1, email: credentials.email }
        
        this.user = user
        this.isAuthenticated = true
        localStorage.setItem('user', JSON.stringify(user))
        
        return true
      } catch (error) {
        console.error('Erreur de connexion:', error)
        return false
      }
    },

    logout() {
      this.user = null
      this.isAuthenticated = false
      localStorage.removeItem('user')
    }
  }
}) 