import axios from "axios"


export const adminAPI = {
    downloadInJSON() {
        return fetch("http://localhost:8011/download/user/json")
    },
    downloadInPDF() {
        return fetch("http://localhost:8011/download/user/pdf")
    }
}

