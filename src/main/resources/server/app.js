const { response } = require("express");
const express = require("express");

const mysql = require('mysql2');
const connection = mysql.createConnection({
    host: '114.71.137.174',
    user: 'mingw',
    password: '1234',
    database: 'jap_mingw',
    port  : 61083
});

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.post("/*", (req, res) => {
    result = req.body;
    console.log(result)

    connection.connect();

    let email = result.email;
    let response = result.results[0].response;

    let params = [email,response];
    connection.query('INSERT INTO FormTable("email","response") VALUES(?,?)', params, (error, rows) => {
        if (error) throw error;
        console.log('User info is: ', rows);
    });

    connection.end();

    res.send(result)
})

app.listen(5045, () => {
    console.log("서버를 구동 중입니다...");
});

