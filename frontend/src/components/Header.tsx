import * as Dialog from '@radix-ui/react-dialog'
import { PlusIcon } from '@radix-ui/react-icons'

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

                {/* TODO: Add Dialog.Portal with the form to add new habit */}
            </Dialog.Root>
        </div>
    )
}

