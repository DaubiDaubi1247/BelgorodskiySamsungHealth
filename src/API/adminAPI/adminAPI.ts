import axios from "axios"


export const adminAPI = {
    downloadInJSON() {
        return fetch("http://app:8011/download/user/json")
    },
    downloadInPDF() {
        return fetch("http://app:8011/download/user/pdf")
    }
}

