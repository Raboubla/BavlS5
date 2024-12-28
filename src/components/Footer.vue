<template>
  <footer class="main-footer">
    <div class="footer-top">
      <div class="container">
        <div class="footer-locations">
          <div class="location">
            <h3>L'AMOUR DU PAIN CENTRE-VILLE</h3>
            <p>393, Rue Principale, Centre-ville</p>
            <p class="phone">01 23 45 67 89</p>
          </div>
          <div class="location">
            <h3>L'AMOUR DU PAIN SUD</h3>
            <p>3050, Boulevard Sud</p>
            <p class="phone">01 23 45 67 90</p>
          </div>
          <div class="location">
            <h3>L'AMOUR DU PAIN NORD</h3>
            <p>323, Avenue du Nord</p>
            <p class="phone">01 23 45 67 91</p>
          </div>
          <div class="location">
            <h3>L'AMOUR DU PAIN EST</h3>
            <p>369, Boulevard Est</p>
            <p class="phone">01 23 45 67 92</p>
          </div>
        </div>
      </div>
    </div>

    <div class="footer-middle">
      <div class="container">
        <div class="footer-nav">
          <div class="footer-section">
            <router-link to="/" class="footer-logo">
              <img src="../assets/boublangerie1.png" alt="Logo" class="footer-logo-img">
            </router-link>
          </div>
          <div class="footer-links">
            <router-link to="/about">À PROPOS</router-link>
            <router-link to="/shop">BOUTIQUE</router-link>
            <router-link to="/careers">CARRIÈRE</router-link>
            <router-link to="/contact">NOUS JOINDRE</router-link>
            <router-link to="/terms">CONDITIONS D'UTILISATIONS</router-link>
          </div>
        </div>
      </div>
    </div>

    <div class="footer-bottom">
      <div class="container">
        <div class="footer-info">
          <p class="copyright">Tous droits réservés © Boulangerie l'Amour du Pain {{ currentYear }}</p>
          <div class="hours">
            <p>Heures d'ouverture:</p>
            <p>Lun-Ven: 6h-19h</p>
            <p>Sam: 6h-18h</p>
            <p>Dim: 6h-17h</p>
          </div>
          <div class="cookies-notice">
            <p>En utilisant ce site, vous acceptez notre utilisation des cookies.</p>
            <p>Nous utilisons des cookies pour vous fournir une expérience améliorée et pour optimiser le bon fonctionnement de notre site web.</p>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup>
const currentYear = new Date().getFullYear()
</script>

<style scoped>
.main-footer {
  background-color: var(--color-light);
  color: var(--color-dark);
  padding-top: 40px;
}

.footer-top {
  padding-bottom: 40px;
}

.footer-locations {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 30px;
}

.location {
  text-align: center;
}

.location h3 {
  color: var(--color-primary);
  font-size: 1.1em;
  margin-bottom: 10px;
  font-weight: 600;
}

.location p {
  margin: 5px 0;
  font-size: 0.9em;
}

.phone {
  color: var(--color-accent);
  font-weight: 600;
}

.footer-middle {
  padding: 40px 0;
  border-top: 1px solid var(--color-secondary);
  border-bottom: 1px solid var(--color-secondary);
}

.footer-nav {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 30px;
}

.footer-logo-img {
  height: 80px;
  margin-bottom: 20px;
}

.footer-links {
  display: flex;
  gap: 30px;
  flex-wrap: wrap;
  justify-content: center;
}

.footer-links a {
  color: var(--color-dark);
  text-decoration: none;
  font-size: 0.9em;
  font-weight: 500;
  transition: color 0.3s;
}

.footer-links a:hover {
  color: var(--color-accent);
}

.footer-bottom {
  padding: 30px 0;
  font-size: 0.9em;
}

.footer-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
  text-align: center;
}

.copyright {
  font-weight: 500;
}

.hours {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
  justify-content: center;
}

.hours p:first-child {
  font-weight: 500;
}

.cookies-notice {
  max-width: 800px;
  margin: 0 auto;
  color: var(--color-text);
  font-size: 0.85em;
}

@media (max-width: 768px) {
  .footer-locations {
    grid-template-columns: 1fr;
  }

  .hours {
    flex-direction: column;
    gap: 5px;
  }

  .footer-links {
    flex-direction: column;
    align-items: center;
    gap: 15px;
  }
}
</style>
