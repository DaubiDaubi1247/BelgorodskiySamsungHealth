import axios from "axios"


export const adminAPI = {
    downloadInJSON(percentOfProgress : number) {
        return fetch(`http://79.143.30.176:8011/download/user/json?percentOfProgress=${percentOfProgress}`)
    },
    downloadInPDF(percentOfProgress : number) {
        return fetch(`http://79.143.30.176:8011/download/user/pdf?percentOfProgress=${percentOfProgress}`)
    }
}

