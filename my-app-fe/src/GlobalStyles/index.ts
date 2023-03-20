import './GlobalStyles.css'
import React from 'react'

type Props = {
    children: JSX.Element
}

function GlobalStyles({children} : Props) {
  return children
}

export default GlobalStyles