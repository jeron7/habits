import { Habit } from './Habit' 

const weekDays = ["D", "S", "T", "Q", "Q", "S", "S"]
const habits = Array((35 * 4)).fill(6)

export function Heatmap() {
    
    return (
        <div className="flex flex-row gap-3">
            <div className="grid grid-rows-7 grid-flow-col gap-2">
                {weekDays.map((weekDay, index) => {
                    return(
                        <div
                            key={index}
                            className="text-zinc-400 text-lg font-bold w-10 h-10 flex items-center justify-center"
                        >
                            {weekDay}
                        </div>
                    )
                })}    
            </div>
            <div className="grid grid-rows-7 grid-flow-col gap-2">
                {
                    habits.map((habit, index) => {
                        return (
                            <Habit key={index} completed={index % habit} amount={6}/>
                        )
                    })
                }
            </div>
        </div>
    )
}