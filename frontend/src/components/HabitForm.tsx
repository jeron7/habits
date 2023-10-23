import { FormEvent, useState } from "react";
import * as Checkbox from "@radix-ui/react-checkbox"
import { CheckIcon } from "@radix-ui/react-icons"

const daysOfWeek = ['Domingo', 'Segunda-feira', 'Terça-feira', 'Quarta-feira', 'Quinta-feira', 'Sexta-feira', 'Sábado']

export function HabitForm() {

    const [title, setTitle] = useState<string>("")
    const [weekDays, setWeekDays] = useState<number[]>([])

    function updateWeekDay(weekDay : number) {
        if (!weekDays.includes(weekDay)) {
            const newWeekDays = [...weekDays, weekDay];
            setWeekDays(newWeekDays)
        } else {
            setWeekDays(weekDays.filter(wd => wd != weekDay))
        }
    }

    function createNewHabit(event: FormEvent) {
        event.preventDefault();
    
        const habit = {
            title,
            weekDays
        }

        if (title && weekDays.length > 0) {
            console.log(habit)
            setTitle("")
            setWeekDays([])
        }
      }

    return (
        <form onSubmit={createNewHabit} className="mt-6 flex flex-col gap-3">
            <label className="font-inter text-white" htmlFor="title-input">Qual o seu compromentimento?</label>
            <input 
                className="font-inter w-full h-12 bg-zinc-800 rounded-lg p-4 text-white placeholder:text-zinc-400" 
                id="title-input" 
                type="text" 
                placeholder="Exercícios, dormir bem, etc.."
                autoFocus
                value={title}
                onChange={ (e) => setTitle(e.target.value) }
            />
            <p className="font-inter text-white mt-3">Qual a recorrência?</p>
            {daysOfWeek.map((day, index) => {
                return (
                    <div className="flex flex-row gap-3 items-center">
                        <Checkbox.Root
                            id={day}
                            key={index}
                            checked={weekDays.includes(index)}
                            className="flex flex-row gap-3 items-center group"
                            onCheckedChange={() => updateWeekDay(index)}
                        >
                            <div className="w-8 h-8 rounded-lg bg-zinc-900 border-2 border-zinc-800 flex justify-center items-center group-data-[state=checked]:bg-green-500 group-data-[state=checked]:border-0">
                                <Checkbox.Indicator>
                                    <CheckIcon className="w-5 h-5 text-white" />
                                </Checkbox.Indicator>
                            </div>
                        </Checkbox.Root>
                        <label className="font-inter text-white" htmlFor={day}>{day}</label>
                    </div>
                    
                )
            })}
            <button 
                type="submit"
                className='w-full h-12 bg-green-600 rounded-xl flex flex-row items-center justify-center gap-3 font-inter text-white font-semibold mt-3'
            >
                <CheckIcon className='w-6 h-6'/>
                Confimar
            </button>
        </form>
    )
}