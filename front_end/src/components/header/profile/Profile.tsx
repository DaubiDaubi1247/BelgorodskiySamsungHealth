import React from 'react'
import { Link } from 'react-router-dom'
import { useAppSelector } from '../../../app/hooks'

type Props = {}

const Profile = (props: Props) => {
    let login = useAppSelector(state => state.auth.accessData?.login)
    return (
        <Link to="/profile">{login}</Link>
    )
}

export default Profile