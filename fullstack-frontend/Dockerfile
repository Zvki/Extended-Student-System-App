# Obraz bazowy dla developmentu Angulara
FROM node:18

# Ustawienie katalogu roboczego
WORKDIR /app

# Kopiowanie plików package.json i instalacja zależności
COPY package.json package-lock.json ./
RUN npm install

# Kopiowanie reszty projektu
COPY . .

# Eksponowanie portu dla Angulara
EXPOSE 4200

# Uruchomienie aplikacji w trybie deweloperskim
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0"]