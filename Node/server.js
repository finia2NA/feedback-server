const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');
const fs = require('fs');
const uuid = require('uuid');

const app = express();

app.use(express.static(path.join(__dirname, 'public')));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/', (req, res) => {
    res.sendFile('index.html', { root: path.join(__dirname, 'public') });
});

app.post('/', (req, res) => {
    fs.writeFile(path.join(__dirname, `/data/${uuid.v4()}.txt`), `${req.body.name}\n${req.body.data}`, err => {
        if (err) {
            console.log(err);
            res.status(500).send('An error occured while storing the data');
            return;
        }

        console.log('File created.');
        res.redirect('/');
    });
});

app.listen(3000, () => console.log('Server running on port 3000'));
