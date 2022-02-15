import Vue from 'vue'
import VueRouter from 'vue-router';
// import App from '@/App'
import Flexbox from '@/components/Flexbox'
import TabContainer from '@/components/TabContainer'

Vue.use(VueRouter)

const routes = [
    { 
        path: '/tab', 
        component: TabContainer
    },
    { 
        path: '/flexbox', 
        component: Flexbox 
    },
    {
        path: '/vslot',
        // 이런 문법도 있음
        component: () => import('@/components/SlotContainer')
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router;