<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pokemon Card</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <div class="card">
      <div class="card-image">
        <img src="/img/${pokemon.name}.png" alt="${pokemon.name}">
      </div>
      <div class="card-info">
        <h2 class="card-title">${pokemon.name}</h2>
        <ul class="card-details">
          <li><strong>Height:</strong> ${pokemon.height}</li>
          <li><strong>Weight:</strong> ${pokemon.weight}</li>
        </ul>
      </div>
    </div>
</body>
</html>