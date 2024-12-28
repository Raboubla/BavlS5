<template>
  <div class="home">
    <!-- Hero Section -->
    <section class="hero">
      <div class="hero-content">
        <h1>Artisans de la farine et du bonheur</h1>
        <router-link to="/shop" class="cta-button">Commander maintenant</router-link>
      </div>
      <div class="awards">
        <div class="award">
          <span class="award-year">2024</span>
          <h3>Meilleure Baguette de la ville</h3>
        </div>
        <div class="award">
          <span class="award-year">2024</span>
          <h3>2ème Meilleur croissant artisanal</h3>
        </div>
      </div>
    </section>

    <!-- About Section -->
    <section class="about">
      <div class="container">
        <div class="about-content">
          <p class="lead-text">
            Née de l'amour d'un artisan français passionné, notre boulangerie a trouvé 
            le secret pour faire succomber les cœurs et les ventres gourmands avec sa 
            vaste sélection de produits de boulangerie, de viennoiserie et de pâtisserie.
          </p>
          <p>
            Chacune de nos succursales offre aussi un comptoir de prêt-à-manger. 
            Sandwichs, salades, soupes, nous avons tout ce qu'il vous faut!
          </p>
        </div>
      </div>
    </section>

    <!-- Services Grid -->
    <section class="services">
      <div class="container">
        <div class="services-grid">
          <div class="service-card">
            <div class="service-image">
              <img src="../assets/blog.jpg" alt="Blog">
            </div>
            <div class="service-content">
              <h2>Blog</h2>
              <p>Conseils, recettes, information complémentaire et plus encore, découvrez tous nos articles de blog.</p>
              <router-link to="/blog" class="btn-link">Lire plus</router-link>
            </div>
          </div>

          <div class="service-card">
            <div class="service-image">
              <img src="../assets/commande.jpg" alt="Commande">
            </div>
            <div class="service-content">
              <h2>Commandez</h2>
              <p>En quelques clics seulement, vous pourrez réserver tous vos produits préférés.</p>
              <router-link to="/shop" class="btn-link">Réserver</router-link>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Quote Section -->
    <section class="quote">
      <div class="container">
        <blockquote>
          "Longues fermentations, boulangers d'expérience et farines locales!"
          <cite>tout pour des produits de qualité</cite>
        </blockquote>
      </div>
    </section>

    <!-- Locations -->
    <section class="locations">
      <div class="container">
        <div class="locations-grid">
          <div class="location-card">
            <h3>Notre Boulangerie Centre-ville</h3>
            <p>123 Rue Principale<br>Centre-ville, 75001</p>
            <p class="phone">01 23 45 67 89</p>
          </div>
          <div class="location-card">
            <h3>Notre Boulangerie Sud</h3>
            <p>456 Avenue du Sud<br>Quartier Sud, 75002</p>
            <p class="phone">01 23 45 67 90</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.hero {
  height: 100vh;
  background-image: linear-gradient(rgba(44, 24, 16, 0.7), rgba(44, 24, 16, 0.7)), url('../assets/bread.jpg');
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  color: white;
  position: relative;
}

.hero-content {
  max-width: 800px;
  padding: 0 20px;
}

.hero-content h1 {
  font-size: 4em;
  margin-bottom: 30px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.cta-button {
  display: inline-block;
  padding: 15px 40px;
  background-color: var(--color-primary);
  color: var(--color-light);
  text-decoration: none;
  border: none;
  border-radius: 30px;
  font-size: 1.2em;
  cursor: pointer;
  transition: background-color 0.3s;
}

.cta-button:hover {
  background-color: var(--color-accent);
}

.awards {
  position: absolute;
  bottom: 50px;
  display: flex;
  gap: 30px;
  padding: 20px;
}

.award {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(5px);
  padding: 20px;
  border-radius: 8px;
  text-align: center;
}

.award-year {
  display: block;
  font-size: 1.2em;
  margin-bottom: 5px;
  color: var(--color-gold);
}

.award h3 {
  font-size: 1.1em;
  font-weight: 500;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 60px 15px;
}

.about {
  background-color: var(--color-light);
  text-align: center;
}

.about-content {
  max-width: 800px;
  margin: 0 auto;
}

.lead-text {
  font-size: 1.5em;
  color: var(--color-dark);
  margin-bottom: 30px;
  line-height: 1.6;
}

.services-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 30px;
}

.service-card {
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  border: 1px solid var(--color-secondary);
}

.service-image {
  height: 300px;
  overflow: hidden;
}

.service-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.service-content {
  padding: 30px;
  text-align: center;
}

.service-content h2 {
  color: var(--color-dark);
  margin-bottom: 15px;
}

.btn-link {
  display: inline-block;
  padding: 10px 25px;
  color: var(--color-dark);
  text-decoration: none;
  border: 2px solid var(--color-primary);
  border-radius: 25px;
  margin-top: 20px;
  transition: all 0.3s;
}

.btn-link:hover {
  background: var(--color-primary);
  color: var(--color-light);
}

.quote {
  background-color: var(--color-primary);
  color: var(--color-light);
  text-align: center;
  padding: 60px 0;
}

blockquote {
  font-size: 2em;
  font-style: italic;
  margin: 0;
}

cite {
  display: block;
  margin-top: 20px;
  font-size: 0.5em;
  text-transform: uppercase;
  letter-spacing: 2px;
}

.locations-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 30px;
}

.location-card {
  padding: 30px;
  background: white;
  border-radius: 8px;
  text-align: center;
  border: 1px solid var(--color-secondary);
}

.location-card h3 {
  color: var(--color-dark);
  margin-bottom: 15px;
}

.phone {
  color: var(--color-accent);
  margin-top: 10px;
  font-weight: bold;
}

@media (max-width: 768px) {
  .hero-content h1 {
    font-size: 2.5em;
  }

  .services-grid {
    grid-template-columns: 1fr;
  }

  .awards {
    flex-direction: column;
    gap: 15px;
  }

  .lead-text {
    font-size: 1.2em;
  }

  blockquote {
    font-size: 1.5em;
  }
}
</style> 