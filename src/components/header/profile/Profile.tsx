import React from 'react'
import { Link } from 'react-router-dom'
import { useAppSelector } from '../../../app/hooks'
import styles from "../header.module.css"

type Props = {}

const Profile = (props: Props) => {
    let login = useAppSelector(state => state.auth.accessData?.login)
    return (
        <Link className={styles.profile} to="/profile">{login}</Link>
    )
}

export default Profile