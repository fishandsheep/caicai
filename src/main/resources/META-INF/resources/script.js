const messages = document.querySelector('.messages');
const input = document.querySelector('input');
const button = document.querySelector('button');

button.addEventListener('click', () => {
  const message = input.value;
  input.value = '';

  const messageElement = document.createElement('div');
  messageElement.classList.add('message');
  messageElement.classList.add('chat-self');
  messageElement.textContent = message;
  messages.appendChild(messageElement);

  fetch('/gpt', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      prompt: message
    })
  })
    .then(response => response.text())
    .then(data => {
        const botMessageElement = document.createElement('div');
        botMessageElement.classList.add('message');
        botMessageElement.classList.add('chat-friend');
       botMessageElement.textContent = data;
       messages.appendChild(botMessageElement);
      });
});

input.addEventListener('keydown', (event) => {
  if (event.key === 'Enter') {
    button.click();
  }
});
