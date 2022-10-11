import requset from '@/network'

// 获取书籍种类
export function getCategory() {
  return requset({
    url: '/category',
    method: 'post'
  })
}

// 获取所有书籍
export function getAllBooks() {
  return requset({
    url: '/getAllBooks',
    method: 'post',
  })
}

// 获取不同种类的书籍，参数：种类的ID
export function get_books_by_category(cid) {
  return requset({
    url: '/getCategoryBooks/'+ cid,
    method: 'post'
  })
}

export function get_book(book_id) {
  return requset({
    url: "/json/details/"+book_id+".json",
    method: 'get'
  })
}

export function searchBook(val) {
  return requset({
    url: '/searchBook/' + val,
    method: 'post'
  })
}
