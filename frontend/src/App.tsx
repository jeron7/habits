import { Header } from './components/Header'
import { Heatmap } from './components/Heatmap'

function App() {
  return (
    <div 
      className="w-screen h-screen bg-zinc-950 flex flex-col items-center justify-center"
    >
      <div className='w-full max-w-4xl flex flex-col gap-y-16 items-center justify-center'>
        <Header />
        <Heatmap />
      </div>
    </div>
  )
}

export default App