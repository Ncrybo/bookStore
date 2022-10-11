import request from '@/newwork'

export function addAmount(id) {
  return request({
    url: '/addAmount/' + id,
    method: 'post'
  })
}

export function searchCart() {
  return request({
    url: '/searchCart',
    method: 'post'
  })
}