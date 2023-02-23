const chatbox = document.querySelector('.chatbox');
const conversation = document.querySelector('.message');
const inputBox = document.querySelector('.input-box');
const input = document.querySelector('input[type="text"]');
const sendBtn = document.querySelector('#send-btn');

// 回车键发送消息
input.addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        sendBtn.click();
    }
});

sendBtn.addEventListener('click', function() {
    const message = input.value;
    if (message.trim() !== '') {
        addMessage('user', message);
        getResponse(message);
        input.value = '';
    }
});

function addMessage(sender, message) {
	
	message = marked.parse(message);
	
    const msgDiv = document.createElement('div');
    
    if (sender === 'bot') {
		msgDiv.classList.add('message-bot');
    } else if (sender === 'user') {	
		msgDiv.classList.add('message-user');
    }
	msgDiv.innerHTML = `<p>${message}</p>`
    conversation.appendChild(msgDiv);
    conversation.scrollTop = conversation.scrollHeight;
}

async function getResponse(message) {
    const url = '/gpt';
    const prompt = `${message}`;
    const body = {
        prompt: prompt
    };
    const headers = {
        'Content-Type': 'application/json',
    };
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(body),
        });
        const botMessage = await response.text();
        addMessage('bot', botMessage);
    } catch (error) {
        console.error(error);
    }
}
