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

  fetch('https://8080-fishandsheep-caicai-n1h87k0yx4z.ws-us87.gitpod.io', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      message: message
    })
  })
    .then(response => response.json())
    .then(data => {
      const botMessage = data.message;

      const botMessageElement = document.createElement('div');
      botMessageElement.classList.add('message');
      botMessageElement.classList.add('chat-friend');
      botMessageElement.textContent = botMessage;
      messages.appendChild(botMessageElement);
    });
});

input.addEventListener('keydown', (event) => {
  if (event.key === 'Enter') {
    button.click();
  }
});
