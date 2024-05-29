document.getElementById('loginForm').addEventListener('submit', function(event) {
    var email = document.getElementById('floatingInput').value;
    var password = document.getElementById('floatingPassword').value;
    if (email === '' || password === '') {
      alert('Por favor, rellene ambos campos.');
      event.preventDefault();
    }
  });