import * as Dialog from '@radix-ui/react-dialog'
import { PlusIcon, Cross2Icon } from '@radix-ui/react-icons'

import { HabitForm } from './HabitForm'

import Logo from '../assets/logo.svg'

export function Header() {
    return (
        <div className="w-full max-w-3xl flex flex-row justify-between">
            <img className="w-36 h-16" src={Logo} alt="Habits" />
            
            <Dialog.Root>
                <Dialog.Trigger
                    type='button'
                    className="h-max border-[2px] text-white border-violet-600 font-semibold rounded-lg px-6 py-3 flex items-center gap-3 hover:border-violet-300 transition-colors focus:outline-none focus:bg-violet-600 focus:ring-offset-background"
                >
                    <PlusIcon className='w-5 h-5' />
                    Novo hábito
                </Dialog.Trigger>

                <Dialog.Portal>
                    <Dialog.Overlay className='fixed inset-0 w-full h-full bg-black/80' />
                    <Dialog.Content className='absolute top-1/2 left-1/2 w-full max-w-md h-fit bg-zinc-900 py-10 px-[39px] rounded-2xl -translate-x-1/2 -translate-y-1/2'>
                        <Dialog.Close className='text-zinc-400 float-right'>
                            <Cross2Icon className='w-6 h-6' aria-label='Fechar'/>
                        </Dialog.Close>
                        <Dialog.Title className='font-inter text-3xl text-white font-extrabold'>Criar hábito</Dialog.Title>
                        
                        <HabitForm />
                    </Dialog.Content>
                </Dialog.Portal>
            </Dialog.Root>
        </div>
    )
}

