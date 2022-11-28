import React from 'react'
import { useAppSelector } from '../../../app/hooks'

type Props = {}

const Profile = (props: Props) => {
    let login = useAppSelector(state => state.auth.accessData?.login)
    return (
        <div>{login}</div>
    )
}

export default Profile